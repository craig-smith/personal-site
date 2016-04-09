package com.craig.entity.userlinks;

import com.craig.entity.base.BaseEntity;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by Craig on 2/19/2016.
 */
@Entity
public class UserLink extends BaseEntity {

    @Column(nullable = false)
    @NotBlank
    private String url;

    @Column(nullable = false)
    private String acl;

    @Column(nullable = false)
    @NotBlank
    private String name;

    @Column(nullable = false)
    @NotBlank
    private String description;

    public UserLink() {}

    public UserLink(String url, String acl, String name, String description) {
        this.url = url;
        this.acl = acl;
        this.name = name;
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAcl() {
        return acl;
    }

    public void setAcl(String acl) {
        this.acl = acl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
