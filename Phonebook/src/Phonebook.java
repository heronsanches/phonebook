import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;


public class Phonebook {
	
	private Map<String, Contact> contacts = null;
	private static Phonebook instance = null;

	
	
	private Phonebook(){
		
		contacts = new TreeMap<String, Contact>();
		initializeContacts(); 
		
	}
	
	
	
	private void initializeContacts(){
		
		try {
			
			FileInputStream fis = new FileInputStream("contacts.pho");
			ObjectInputStream ois = new ObjectInputStream(fis);
			contacts.putAll((Map<String, Contact>)ois.readObject());
			
			ois.close();
			fis.close();
			
		} catch (FileNotFoundException e) {
			
			System.out.println("Phonebook is empty.");
			
		} catch (IOException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (ClassNotFoundException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
	
	
	public void update(String key, Contact contact){
		
		if(key.equals(contact.getName())){
			
			contacts.put(key, new Contact(contact.getName(), contact.getTelNumber()));
			
		}else{
			
			contacts.remove(key);
			insertContact(contact);
			
		}
		
	}
	
	
	/**
	 * deletes contact os "Phonebook.contacts" and update file on disk"
	 */
	public void delete(String key){
		
		Contact c = contacts.remove(key);
		persistContact();
				
	}
	
	
	/**
	 * 
	 * @param name
	 * @return null if contact doesn't exist or the contact if it exist
	 */
	public Contact existsContact(String name){
		
		return contacts.get(name);
		
	}
	
	
	public void insertContact(Contact contact){
		
		contacts.put(contact.getName().toLowerCase(), contact);
		persistContact();
		
	}
	
	
	
	public Map<String, Contact> search(String key){
		
		key.toLowerCase();
		Map<String, Contact> searchResult = new TreeMap<String, Contact>();
		Set<Entry<String, Contact>> setView = contacts.entrySet();
		
		for(Entry<String, Contact> es : setView){
			
			if(es.getKey().contains(key))
				searchResult.put(es.getKey(), es.getValue());
			
		}
		
		return searchResult;
		
	}
	

	/**
	 * It salves the object "Phonebook.contacts" on disk file.
	 * This operation overlap the file on disk with this object "Phonebook.contacts".
	 */
	private void persistContact(){
		try {
			
			FileOutputStream fos = new FileOutputStream("contacts.pho");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(contacts);
			oos.close();
			fos.close();
			
		} catch (FileNotFoundException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (IOException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}

	}	
	
	
	public void showAllContacts(){
		
		Set<Entry<String, Contact>> ec = contacts.entrySet();
		
		for(Entry<String, Contact> c: ec)
			System.out.println("key: "+c.getKey()+"\n"+c.getValue().getName()+"\n"+
				c.getValue().getTelNumber()+"\n");
			
	}
	
	
	public static Phonebook getInstance(){
		
		if(instance == null)
			instance = new Phonebook();
		
		return instance;
	}
	

	public void menuOperations(){

	 Scanner scanner = new Scanner(System.in);
	 String name = "";
	 String telNumber = "";
	 String newName = "";
	 String newTelNumber = "";
	 char opcao;
	
	//MENU
	do{
		clearConsole();
		
		System.out.println("\t\t\t<<<Phonebook>>>");
		System.out.println("1-insert");
		System.out.println("2-edit");
		System.out.println("3-delete");
		System.out.println("4-search");
		//System.out.println("5-showAll");//for testing
		System.out.println("6-exit");//
		
		opcao = scanner.next().charAt(0);
		switch(opcao){
		
			case ('1'):
				
			 scanner = new Scanner(System.in);
			 System.out.print("nome: ");
			 name = scanner.nextLine();
			 System.out.print("telefone: ");
			 telNumber = scanner.nextLine();
			 insertContact(new Contact(name, telNumber));
			 
			break;
			case ('2'): //edit
				
				scanner = new Scanner(System.in);
				System.out.print("nome: ");
				name = scanner.nextLine();
				
				if(existsContact(name) != null){
					
					System.out.print("novo nome: ");
					newName = scanner.nextLine();
					
					System.out.print("novo telefone: ");
					newTelNumber = scanner.nextLine();
					
					update(name, new Contact(newName, newTelNumber));
					
				}else{
					
					System.out.print("Contato nao existe. Pressione enter para voltar ao menu.");
					scanner.nextLine();
					
				}
					
										 
			break;
			case ('3'): //delete
				
				scanner = new Scanner(System.in);
				System.out.print("delete nome: ");
				name = scanner.nextLine();
				
				if(existsContact(name) != null)
					delete(name);
				else{
					
					System.out.print("contato nao existe, pressione enter para voltar ao menu");
					scanner.nextLine();
				}
				
			break;
			case ('4'): //search
				
				scanner = new Scanner(System.in);
				System.out.print("procure nome: ");
				name = scanner.nextLine();

				for(Entry<String, Contact> c: search(name).entrySet())
					System.out.println(c.getKey()+"\n"+c.getValue().getTelNumber()+"\n");
				
				System.out.print("pressione enter para voltar ao menu");
				scanner.nextLine();

			break;
			/*case ('5'):
				
				showAllContacts();
				System.out.print("pressione enter para voltar ao menu");
				scanner.nextLine();
				scanner.nextLine();
				
			break;*/
			case ('6'):
				opcao = '0';
			break;
			default:
				
				System.out.print("Opcao invalida. Pressione enter para voltar ao menu");
				scanner.nextLine();
				scanner.nextLine();
				
			break;
			
		}
					
	}while (opcao != '0');	
}
	
	
	private void clearConsole(){
		
		for(int i=0; i<25; i++)
			System.out.println();

	}

}
