package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import app.entity.Content;

public interface ContentRepository extends JpaRepository<Content,Long>{}