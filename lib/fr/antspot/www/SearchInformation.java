
package fr.antspot.www;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Generated("org.jsonschema2pojo")
public class SearchInformation {

    @Expose
    private Double searchTime;
    @Expose
    private String formattedSearchTime;
    @Expose
    private String totalResults;
    @Expose
    private String formattedTotalResults;

    /**
     * 
     * @return
     *     The searchTime
     */
    public Double getSearchTime() {
        return searchTime;
    }

    /**
     * 
     * @param searchTime
     *     The searchTime
     */
    public void setSearchTime(Double searchTime) {
        this.searchTime = searchTime;
    }

    public SearchInformation withSearchTime(Double searchTime) {
        this.searchTime = searchTime;
        return this;
    }

    /**
     * 
     * @return
     *     The formattedSearchTime
     */
    public String getFormattedSearchTime() {
        return formattedSearchTime;
    }

    /**
     * 
     * @param formattedSearchTime
     *     The formattedSearchTime
     */
    public void setFormattedSearchTime(String formattedSearchTime) {
        this.formattedSearchTime = formattedSearchTime;
    }

    public SearchInformation withFormattedSearchTime(String formattedSearchTime) {
        this.formattedSearchTime = formattedSearchTime;
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

    public SearchInformation withTotalResults(String totalResults) {
        this.totalResults = totalResults;
        return this;
    }

    /**
     * 
     * @return
     *     The formattedTotalResults
     */
    public String getFormattedTotalResults() {
        return formattedTotalResults;
    }

    /**
     * 
     * @param formattedTotalResults
     *     The formattedTotalResults
     */
    public void setFormattedTotalResults(String formattedTotalResults) {
        this.formattedTotalResults = formattedTotalResults;
    }

    public SearchInformation withFormattedTotalResults(String formattedTotalResults) {
        this.formattedTotalResults = formattedTotalResults;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(searchTime).append(formattedSearchTime).append(totalResults).append(formattedTotalResults).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof SearchInformation) == false) {
            return false;
        }
        SearchInformation rhs = ((SearchInformation) other);
        return new EqualsBuilder().append(searchTime, rhs.searchTime).append(formattedSearchTime, rhs.formattedSearchTime).append(totalResults, rhs.totalResults).append(formattedTotalResults, rhs.formattedTotalResults).isEquals();
    }

}
