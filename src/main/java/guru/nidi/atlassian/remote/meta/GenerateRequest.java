package guru.nidi.atlassian.remote.meta;

import java.io.Serializable;
import java.util.Map;

/**
 *
 */
public class GenerateRequest implements Serializable {
    private String url;
    private String template;
    private String templateSrc;
    private String style;
    private String styleSrc;
    private String projectKey;
    private String projectName;
    private String versions;
    private String docType;
    private String status;
    private String author;
    private String collaborators;
    private String distribution;

    private Map<String, String> include;
    private boolean cache;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getTemplateSrc() {
        return templateSrc;
    }

    public void setTemplateSrc(String templateSrc) {
        this.templateSrc = templateSrc;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getStyleSrc() {
        return styleSrc;
    }

    public void setStyleSrc(String styleSrc) {
        this.styleSrc = styleSrc;
    }

    public String getProjectKey() {
        return projectKey;
    }

    public void setProjectKey(String projectKey) {
        this.projectKey = projectKey;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getVersions() {
        return versions;
    }

    public void setVersions(String versions) {
        this.versions = versions;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCollaborators() {
        return collaborators;
    }

    public void setCollaborators(String collaborators) {
        this.collaborators = collaborators;
    }

    public String getDistribution() {
        return distribution;
    }

    public void setDistribution(String distribution) {
        this.distribution = distribution;
    }

    public Map<String, String> getInclude() {
        return include;
    }

    public void setInclude(Map<String, String> include) {
        this.include = include;
    }

    public boolean isCache() {
        return cache;
    }

    public void setCache(boolean cache) {
        this.cache = cache;
    }
}
