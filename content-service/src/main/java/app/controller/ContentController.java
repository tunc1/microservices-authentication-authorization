package app.controller;

import app.entity.Content;
import app.service.ContentService;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Page;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/content")
public class ContentController
{
	private ContentService contentService;
	public ContentController(ContentService contentService)
	{
		this.contentService=contentService;
	}
	@PostMapping
	@ResponseStatus(code=HttpStatus.CREATED)
	public Content save(@RequestBody Content content)
	{
		return contentService.save(content);
	}
	@PutMapping("/{id}")
	public Content update(@RequestBody Content content,@PathVariable Long id)
	{
		content.setId(id);
		return contentService.update(content);
	}
	@GetMapping("/{id}")
	public Content findById(@PathVariable Long id)
	{
		return contentService.findById(id);
	}
	@GetMapping
	public Page<Content> findAll(Pageable pageable)
	{
		return contentService.findAll(pageable);
	}
	@DeleteMapping("/{id}")
	@ResponseStatus(code=HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Long id)
	{
		contentService.deleteById(id);
	}
}