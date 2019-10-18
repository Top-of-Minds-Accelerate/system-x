package se.topofminds.accelerate.SystemX.model;

public class HealthResponse {
    public HealthResponse(String status) {
        this.status = status;
    }

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}


