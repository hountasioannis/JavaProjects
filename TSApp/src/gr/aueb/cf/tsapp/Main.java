package gr.aueb.cf.tsapp;

import java.awt.EventQueue;

public class Main {
	
	private static Menu menu;
	private static SearchForm searchForm;
	private static InsertForm insertForm;
	private static UpdateDeleteForm updateDeleteForm;
	
	private static LoginPage loginPage;
	private static InsertUser insertUser;
	private static SearchUser searchUser;
	private static UpdateDeleteUser updateDeleteUser;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					menu = new Menu();
					menu.setLocationRelativeTo(null);
					menu.setVisible(false);
					
					searchForm = new SearchForm();
					searchForm.setLocationRelativeTo(null);
					searchForm.setVisible(false);
					
					insertForm = new InsertForm();
					insertForm.setLocationRelativeTo(null);
					insertForm.setVisible(false);
					
					updateDeleteForm = new UpdateDeleteForm();
					updateDeleteForm.setLocationRelativeTo(null);
					updateDeleteForm.setVisible(false);
					
					loginPage = new LoginPage();
					loginPage.setLocationRelativeTo(null);
					loginPage.setVisible(true);
					
					insertUser = new InsertUser();
					insertUser.setLocationRelativeTo(null);
					insertUser.setVisible(false);
					
					searchUser = new SearchUser();
					searchUser.setLocationRelativeTo(null);
					searchUser.setVisible(false);
					
					updateDeleteUser = new UpdateDeleteUser();
					updateDeleteUser.setLocationRelativeTo(null);
					updateDeleteUser.setVisible(false);
					
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static Menu getMenu() {
		return menu;
	}

	public static SearchForm getSearchForm() {
		return searchForm;
	}

	public static InsertForm getInsertForm() {
		return insertForm;
	}

	public static UpdateDeleteForm getUpdateDeleteForm() {
		return updateDeleteForm;
	}

	public static LoginPage getLoginPage() {
		return loginPage;
	}

	public static InsertUser getInsertUser() {
		return insertUser;
	}

	public static SearchUser getSearchUser() {
		return searchUser;
	}

	public static UpdateDeleteUser getUpdateDeleteUser() {
		return updateDeleteUser;
	}
	

}
