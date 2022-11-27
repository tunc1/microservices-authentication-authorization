package app.client.response;

import java.io.Serializable;

public record TokenInfo(boolean verified,String role) implements Serializable{}