package br.com.systemsgs.fileserver1.service;

import br.com.systemsgs.fileserver1.entity.ModelFileServer;
import br.com.systemsgs.fileserver1.repository.FIleServerRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class FileStorageService {

    private final FIleServerRepository fileServerRepository;

    public FileStorageService(FIleServerRepository fileServerRepository) {
        this.fileServerRepository = fileServerRepository;
    }

    public ModelFileServer store(MultipartFile file) throws IOException{
        var fileName = StringUtils.cleanPath(file.getOriginalFilename());

        ModelFileServer fileServer = new ModelFileServer(fileName, file.getContentType(), file.getBytes());

        return fileServerRepository.save(fileServer);
    }

    public ModelFileServer getFiles(UUID id){
        return fileServerRepository.findById(id).get();
    }

    public Stream<ModelFileServer> getAllFiles(){
        return fileServerRepository.findAll().stream();
    }
}
