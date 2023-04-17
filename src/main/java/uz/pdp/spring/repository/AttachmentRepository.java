package uz.pdp.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.spring.entity.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {
}
