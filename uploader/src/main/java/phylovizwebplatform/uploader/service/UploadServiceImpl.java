package phylovizwebplatform.uploader.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import phylovizwebplatform.uploader.http.models.FileType;
import phylovizwebplatform.uploader.repository.UploadRepository;
import phylovizwebplatform.uploader.repository.metadata.UploadMetadataRepository;
import phylovizwebplatform.uploader.repository.metadata.objects.Metadata;
import phylovizwebplatform.uploader.repository.metadata.objects.ProfileMetadata;

import java.util.UUID;

@Service
public class UploadServiceImpl implements UploadService {

    private final UploadRepository uploadRepository;
    private final UploadMetadataRepository uploadMetadataRepository;

    public UploadServiceImpl(UploadRepository uploadRepository, UploadMetadataRepository uploadMetadataRepository) {
        this.uploadRepository = uploadRepository;
        this.uploadMetadataRepository = uploadMetadataRepository;
    }

    @Override
    public void store(String projectName, FileType fileType, MultipartFile multipartFile) {
        UUID id = UUID.randomUUID();

        String location = "\\" + projectName + "\\" + id + fileType.getFileExtension();

        final Metadata metadata = switch (fileType) {
            case PROFILE, FASTA, NEWICK, AUXILIARY ->
                    new ProfileMetadata(projectName, uploadRepository.getLocation() + location,
                            fileType.toString(), multipartFile.getOriginalFilename());
        };

        uploadMetadataRepository.store(metadata);

        boolean stored = uploadRepository.store(location, multipartFile);

        if (!stored) {
            throw new RuntimeException("Could not store file");
        }
    }
}
