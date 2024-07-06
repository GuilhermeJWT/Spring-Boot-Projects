package br.com.systemsgs.fileserver1.controller;

import br.com.systemsgs.fileserver1.dto.FileResponseDTO;
import br.com.systemsgs.fileserver1.dto.ResponseMessage;
import br.com.systemsgs.fileserver1.entity.ModelFileServer;
import br.com.systemsgs.fileserver1.service.FileStorageService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class FileServerController {

    private final FileStorageService fileStorageService;

    public FileServerController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            fileStorageService.store(file);

            message = "Arquivo carregado: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Erro ao fazer Upload do arquivo: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/files")
    public ResponseEntity<List<FileResponseDTO>> getListFiles() {
        List<FileResponseDTO> files = fileStorageService.getAllFiles().map(fileServer -> {
            var fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(String.valueOf(fileServer.getId()))
                    .toUriString();

            return new FileResponseDTO(
                    fileServer.getName(),
                    fileDownloadUri,
                    fileServer.getType(),
                    (long) fileServer.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable UUID id) {
        ModelFileServer fileDB = fileStorageService.getFiles(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
                .body(fileDB.getData());
    }
}
