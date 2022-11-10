package app.service;

import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import app.entity.Content;
import app.repository.ContentRepository;
import javax.persistence.EntityNotFoundException;

@Service
public class ContentService
{
	private ContentRepository contentRepository;
	public ContentService(ContentRepository contentRepository)
	{
		this.contentRepository=contentRepository;
	}
	public Content save(Content content)
	{
		return contentRepository.save(content);
	}
	public Content update(Content content)
	{
		return contentRepository.save(content);
	}
	public void deleteById(Long id)
	{
		contentRepository.deleteById(id);
	}
	public Content findById(Long id)
	{
		return contentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
	}
	public Page<Content> findAll(Pageable pageable)
	{
		return contentRepository.findAll(pageable);
	}
}