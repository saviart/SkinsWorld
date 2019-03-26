package net.skinsworld.model;

public class TopUser {
    private String PersonaName;
    private String InvitationCode;
    private String ImageURL;
    private String TotalCoins;

    public String getPersonaName() {
        return PersonaName;
    }

    public void setPersonaName(String personaName) {
        PersonaName = personaName;
    }

    public String getInvitationCode() {
        return InvitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        InvitationCode = invitationCode;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }

    public String getTotalCoins() {
        return TotalCoins;
    }

    public void setTotalCoins(String totalCoins) {
        TotalCoins = totalCoins;
    }
}
