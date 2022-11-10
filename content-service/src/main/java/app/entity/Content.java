package app.entity;

import javax.persistence.*;

@Entity
public class Content
{
    @Id
    @SequenceGenerator(name="Content_SEQUENCE_GENERATOR",sequenceName="Content_SEQUENCE",allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="Content_SEQUENCE_GENERATOR")
    private Long id;
    private Long userId;
    private String header,content;
    public Long getId()
    {
        return id;
    }
    public void setId(Long id)
    {
        this.id=id;
    }
    public Long getUserId()
    {
        return userId;
    }
    public void setUserId(Long userId)
    {
        this.userId=userId;
    }
    public String getHeader()
    {
        return header;
    }
    public void setHeader(String header)
    {
        this.header=header;
    }
    public String getContent()
    {
        return content;
    }
    public void setContent(String content)
    {
        this.content=content;
    }
}