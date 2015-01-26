
package fr.antspot.www;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Generated("org.jsonschema2pojo")
public class GoogleSearchResult {

    @Expose
    private String kind;
    @Expose
    private Url url;
    @Expose
    private Queries queries;
    @Expose
    private Context context;
    @Expose
    private SearchInformation searchInformation;
    @Expose
    private List<Item> items = new ArrayList<Item>();

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

    public GoogleSearchResult withKind(String kind) {
        this.kind = kind;
        return this;
    }

    /**
     * 
     * @return
     *     The url
     */
    public Url getUrl() {
        return url;
    }

    /**
     * 
     * @param url
     *     The url
     */
    public void setUrl(Url url) {
        this.url = url;
    }

    public GoogleSearchResult withUrl(Url url) {
        this.url = url;
        return this;
    }

    /**
     * 
     * @return
     *     The queries
     */
    public Queries getQueries() {
        return queries;
    }

    /**
     * 
     * @param queries
     *     The queries
     */
    public void setQueries(Queries queries) {
        this.queries = queries;
    }

    public GoogleSearchResult withQueries(Queries queries) {
        this.queries = queries;
        return this;
    }

    /**
     * 
     * @return
     *     The context
     */
    public Context getContext() {
        return context;
    }

    /**
     * 
     * @param context
     *     The context
     */
    public void setContext(Context context) {
        this.context = context;
    }

    public GoogleSearchResult withContext(Context context) {
        this.context = context;
        return this;
    }

    /**
     * 
     * @return
     *     The searchInformation
     */
    public SearchInformation getSearchInformation() {
        return searchInformation;
    }

    /**
     * 
     * @param searchInformation
     *     The searchInformation
     */
    public void setSearchInformation(SearchInformation searchInformation) {
        this.searchInformation = searchInformation;
    }

    public GoogleSearchResult withSearchInformation(SearchInformation searchInformation) {
        this.searchInformation = searchInformation;
        return this;
    }

    /**
     * 
     * @return
     *     The items
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * 
     * @param items
     *     The items
     */
    public void setItems(List<Item> items) {
        this.items = items;
    }

    public GoogleSearchResult withItems(List<Item> items) {
        this.items = items;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(kind).append(url).append(queries).append(context).append(searchInformation).append(items).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof GoogleSearchResult) == false) {
            return false;
        }
        GoogleSearchResult rhs = ((GoogleSearchResult) other);
        return new EqualsBuilder().append(kind, rhs.kind).append(url, rhs.url).append(queries, rhs.queries).append(context, rhs.context).append(searchInformation, rhs.searchInformation).append(items, rhs.items).isEquals();
    }

}
