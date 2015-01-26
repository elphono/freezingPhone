
package fr.antspot.www;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Generated("org.jsonschema2pojo")
public class Item {

    @Expose
    private String kind;
    @Expose
    private String title;
    @Expose
    private String htmlTitle;
    @Expose
    private String link;
    @Expose
    private String displayLink;
    @Expose
    private String snippet;
    @Expose
    private String htmlSnippet;
    @Expose
    private String cacheId;
    @Expose
    private String formattedUrl;
    @Expose
    private String htmlFormattedUrl;
    @Expose
    private Pagemap pagemap;
    @Expose
    private String mime;
    @Expose
    private String fileFormat;

    /**
     * 
     * @return
     *     The kind
     */
    public String getKind() {
        return kind;
    }

    /**
     * 
     * @param kind
     *     The kind
     */
    public void setKind(String kind) {
        this.kind = kind;
    }

    public Item withKind(String kind) {
        this.kind = kind;
        return this;
    }

    /**
     * 
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    public Item withTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * 
     * @return
     *     The htmlTitle
     */
    public String getHtmlTitle() {
        return htmlTitle;
    }

    /**
     * 
     * @param htmlTitle
     *     The htmlTitle
     */
    public void setHtmlTitle(String htmlTitle) {
        this.htmlTitle = htmlTitle;
    }

    public Item withHtmlTitle(String htmlTitle) {
        this.htmlTitle = htmlTitle;
        return this;
    }

    /**
     * 
     * @return
     *     The link
     */
    public String getLink() {
        return link;
    }

    /**
     * 
     * @param link
     *     The link
     */
    public void setLink(String link) {
        this.link = link;
    }

    public Item withLink(String link) {
        this.link = link;
        return this;
    }

    /**
     * 
     * @return
     *     The displayLink
     */
    public String getDisplayLink() {
        return displayLink;
    }

    /**
     * 
     * @param displayLink
     *     The displayLink
     */
    public void setDisplayLink(String displayLink) {
        this.displayLink = displayLink;
    }

    public Item withDisplayLink(String displayLink) {
        this.displayLink = displayLink;
        return this;
    }

    /**
     * 
     * @return
     *     The snippet
     */
    public String getSnippet() {
        return snippet;
    }

    /**
     * 
     * @param snippet
     *     The snippet
     */
    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public Item withSnippet(String snippet) {
        this.snippet = snippet;
        return this;
    }

    /**
     * 
     * @return
     *     The htmlSnippet
     */
    public String getHtmlSnippet() {
        return htmlSnippet;
    }

    /**
     * 
     * @param htmlSnippet
     *     The htmlSnippet
     */
    public void setHtmlSnippet(String htmlSnippet) {
        this.htmlSnippet = htmlSnippet;
    }

    public Item withHtmlSnippet(String htmlSnippet) {
        this.htmlSnippet = htmlSnippet;
        return this;
    }

    /**
     * 
     * @return
     *     The cacheId
     */
    public String getCacheId() {
        return cacheId;
    }

    /**
     * 
     * @param cacheId
     *     The cacheId
     */
    public void setCacheId(String cacheId) {
        this.cacheId = cacheId;
    }

    public Item withCacheId(String cacheId) {
        this.cacheId = cacheId;
        return this;
    }

    /**
     * 
     * @return
     *     The formattedUrl
     */
    public String getFormattedUrl() {
        return formattedUrl;
    }

    /**
     * 
     * @param formattedUrl
     *     The formattedUrl
     */
    public void setFormattedUrl(String formattedUrl) {
        this.formattedUrl = formattedUrl;
    }

    public Item withFormattedUrl(String formattedUrl) {
        this.formattedUrl = formattedUrl;
        return this;
    }

    /**
     * 
     * @return
     *     The htmlFormattedUrl
     */
    public String getHtmlFormattedUrl() {
        return htmlFormattedUrl;
    }

    /**
     * 
     * @param htmlFormattedUrl
     *     The htmlFormattedUrl
     */
    public void setHtmlFormattedUrl(String htmlFormattedUrl) {
        this.htmlFormattedUrl = htmlFormattedUrl;
    }

    public Item withHtmlFormattedUrl(String htmlFormattedUrl) {
        this.htmlFormattedUrl = htmlFormattedUrl;
        return this;
    }

    /**
     * 
     * @return
     *     The pagemap
     */
    public Pagemap getPagemap() {
        return pagemap;
    }

    /**
     * 
     * @param pagemap
     *     The pagemap
     */
    public void setPagemap(Pagemap pagemap) {
        this.pagemap = pagemap;
    }

    public Item withPagemap(Pagemap pagemap) {
        this.pagemap = pagemap;
        return this;
    }

    /**
     * 
     * @return
     *     The mime
     */
    public String getMime() {
        return mime;
    }

    /**
     * 
     * @param mime
     *     The mime
     */
    public void setMime(String mime) {
        this.mime = mime;
    }

    public Item withMime(String mime) {
        this.mime = mime;
        return this;
    }

    /**
     * 
     * @return
     *     The fileFormat
     */
    public String getFileFormat() {
        return fileFormat;
    }

    /**
     * 
     * @param fileFormat
     *     The fileFormat
     */
    public void setFileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
    }

    public Item withFileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(kind).append(title).append(htmlTitle).append(link).append(displayLink).append(snippet).append(htmlSnippet).append(cacheId).append(formattedUrl).append(htmlFormattedUrl).append(pagemap).append(mime).append(fileFormat).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Item) == false) {
            return false;
        }
        Item rhs = ((Item) other);
        return new EqualsBuilder().append(kind, rhs.kind).append(title, rhs.title).append(htmlTitle, rhs.htmlTitle).append(link, rhs.link).append(displayLink, rhs.displayLink).append(snippet, rhs.snippet).append(htmlSnippet, rhs.htmlSnippet).append(cacheId, rhs.cacheId).append(formattedUrl, rhs.formattedUrl).append(htmlFormattedUrl, rhs.htmlFormattedUrl).append(pagemap, rhs.pagemap).append(mime, rhs.mime).append(fileFormat, rhs.fileFormat).isEquals();
    }

}
