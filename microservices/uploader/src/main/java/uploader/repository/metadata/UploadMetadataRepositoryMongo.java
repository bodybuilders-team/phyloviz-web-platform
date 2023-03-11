package uploader.repository.metadata;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import uploader.repository.metadata.mongo.MetadataMongoRepository;
import uploader.repository.metadata.mongo.ProjectMongoRepository;
import uploader.repository.metadata.objects.Metadata;
import uploader.repository.project.Project;

/**
 * Implementation of the {@link UploadMetadataRepository} interface that stores the metadata in a MongoDB database.
 */
@Repository
@Primary
public class UploadMetadataRepositoryMongo implements UploadMetadataRepository {

    private final MetadataMongoRepository metadataMongoRepository;
    private final ProjectMongoRepository projectMongoRepository;

    public UploadMetadataRepositoryMongo(
            MetadataMongoRepository metadataMongoRepository,
            ProjectMongoRepository projectMongoRepository
    ) {
        this.metadataMongoRepository = metadataMongoRepository;
        this.projectMongoRepository = projectMongoRepository;
    }

    @Override
    public Metadata store(Metadata metadata) {
        return metadataMongoRepository.save(metadata);
    }

    @Override
    public void storeProject(Project project) {
        projectMongoRepository.save(project);
    }
}
