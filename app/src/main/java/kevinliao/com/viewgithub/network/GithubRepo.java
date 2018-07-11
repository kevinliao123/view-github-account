package kevinliao.com.viewgithub.network;

import com.google.gson.annotations.SerializedName;

public class GithubRepo {
    @SerializedName("full_name")
    String fullName;
    @SerializedName("updated_at")
    String updatedAt;
    String description;
    @SerializedName("html_url")
    String url;

    public GithubRepo(String fullName, String updatedAt, String description, String url) {
        this.fullName = fullName;
        this.updatedAt = updatedAt;
        this.description = description;
        this.url = url;
    }

    public GithubRepo() {

    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
