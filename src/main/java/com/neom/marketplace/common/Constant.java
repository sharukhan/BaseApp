package com.neom.marketplace.common;

public final class Constant {

	public static final String ON_CREATE_SUCCESS = "item created";
	public static final String ON_CREATE_DUP_ERROR = "an existing item already exists";
	public static final String ON_UPDATE_SUCCESS = "Updated %s object";
	public static final String ON_PUT_INPUT_ERROR = "Invalid %s supplied";
	public static final String ON_DELETE_INPUT_ERROR = "Invalid ID supplied";
	public static final String ON_CREATE_INPUT_ERROR = "invalid input, object invalid";
	public static enum  Entities {
		Client,
		User,
		Role,
		Supplier,
		Bim,
		BimInstance
	};
}
