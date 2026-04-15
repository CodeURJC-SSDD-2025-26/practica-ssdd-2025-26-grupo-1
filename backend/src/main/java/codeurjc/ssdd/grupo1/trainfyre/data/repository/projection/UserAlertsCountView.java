package codeurjc.ssdd.grupo1.trainfyre.data.repository.projection;

public interface UserAlertsCountView {
    Long getUserId();
    String getUsername();
    String  getEmail();
    Long getTotalAlerts();
}

