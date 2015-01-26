
package fr.antspot.www;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Generated("org.jsonschema2pojo")
public class Request {

    @Expose
    private String title;
    @Expose
    private String totalResults;
    @Expose
    private String searchTerms;
    @Expose
    private Integer count;
    @Expose
    private Integer startIndex;
    @Expose
    private String inputEncoding;
    @Expose
    private String outputEncoding;
    @Expose
    private String safe;
    @Expose
    private String cx;

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

    public Request withTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * 
     * @return
     *     The totalResults
     */
    public String getTotalResults() {
        return totalResults;
    }

    /**
     * 
     * @param totalResults
     *     The totalResults
     */
    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public Request withTotalResults(String totalResults) {
        this.totalResults = totalResults;
        return this;
    }

    /**
     * 
     * @return
     *     The searchTerms
     */
    public String getSearchTerms() {
        return searchTerms;
    }

    /**
     * 
     * @param searchTerms
     *     The searchTerms
     */
    public void setSearchTerms(String searchTerms) {
        this.searchTerms = searchTerms;
    }

    public Request withSearchTerms(String searchTerms) {
        this.searchTerms = searchTerms;
        return this;
    }

    /**
     * 
     * @return
     *     The count
     */
    public Integer getCount() {
        return count;
    }

    /**
     * 
     * @param count
     *     The count
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    public Request withCount(Integer count) {
        this.count = count;
        return this;
    }

    /**
     * 
     * @return
     *     The startIndex
     */
    public Integer getStartIndex() {
        return startIndex;
    }

    /**
     * 
     * @param startIndex
     *     The startIndex
     */
    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public Request withStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
        return this;
    }

    /**
     * 
     * @return
     *     The inputEncoding
     */
    public String getInputEncoding() {
        return inputEncoding;
    }

    /**
     * 
     * @param inputEncoding
     *     The inputEncoding
     */
    public void setInputEncoding(String inputEncoding) {
        this.inputEncoding = inputEncoding;
    }

    public Request withInputEncoding(String inputEncoding) {
        this.inputEncoding = inputEncoding;
        return this;
    }

    /**
     * 
     * @return
     *     The outputEncoding
     */
    public String getOutputEncoding() {
        return outputEncoding;
    }

    /**
     * 
     * @param outputEncoding
     *     The outputEncoding
     */
    public void setOutputEncoding(String outputEncoding) {
        this.outputEncoding = outputEncoding;
    }

    public Request withOutputEncoding(String outputEncoding) {
        this.outputEncoding = outputEncoding;
        return this;
    }

    /**
     * 
     * @return
     *     The safe
     */
    public String getSafe() {
        return safe;
    }

    /**
     * 
     * @param safe
     *     The safe
     */
    public void setSafe(String safe) {
        this.safe = safe;
    }

    public Request withSafe(String safe) {
        this.safe = safe;
        return this;
    }

    /**
     * 
     * @return
     *     The cx
     */
    public String getCx() {
        return cx;
    }

    /**
     * 
     * @param cx
     *     The cx
     */
    public void setCx(String cx) {
        this.cx = cx;
    }

    public Request withCx(String cx) {
        this.cx = cx;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(title).append(totalResults).append(searchTerms).append(count).append(startIndex).append(inputEncoding).append(outputEncoding).append(safe).append(cx).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Request) == false) {
            return false;
        }
        Request rhs = ((Request) other);
        return new EqualsBuilder().append(title, rhs.title).append(totalResults, rhs.totalResults).append(searchTerms, rhs.searchTerms).append(count, rhs.count).append(startIndex, rhs.startIndex).append(inputEncoding, rhs.inputEncoding).append(outputEncoding, rhs.outputEncoding).append(safe, rhs.safe).append(cx, rhs.cx).isEquals();
    }

}
