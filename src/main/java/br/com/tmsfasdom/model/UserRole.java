package br.com.tmsfasdom.model;

public enum UserRole {
	USER, ADMIN;

	public Autorizacao asAuthorityFor(final Usuario usuario) {
		final Autorizacao authority = new Autorizacao();
		authority.setDescAutorizacao("ROLE_" + this.toString());
		authority.getUsuarios().add(usuario);
		return authority;
	}

	public static UserRole valueOf(final Autorizacao authority) {
		switch (authority.getAuthority()) {
		case "ROLE_USER":
			return USER;
		case "ROLE_ADMIN":
			return ADMIN;
		}
		throw new IllegalArgumentException("No role defined for authority: " + authority.getAuthority());
	}
}
