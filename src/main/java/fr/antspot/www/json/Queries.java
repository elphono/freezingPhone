
package fr.antspot.www.json;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Generated("org.jsonschema2pojo")
public class Queries {

    @Expose
    private List<Request> request = new ArrayList<Request>();

    /**
     * 
     * @return
     *     The request
     */
    public List<Request> getRequest() {
        return request;
    }

    /**
     * 
     * @param request
     *     The request
     */
    public void setRequest(List<Request> request) {
        this.request = request;
    }

    public Queries withRequest(List<Request> request) {
        this.request = request;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(request).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Queries) == false) {
            return false;
        }
        Queries rhs = ((Queries) other);
        return new EqualsBuilder().append(request, rhs.request).isEquals();
    }

}
