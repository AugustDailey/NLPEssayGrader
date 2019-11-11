package application;

public class Application {
	
	private NLPGui nlpGui;
	
	public Application() {
		
	}
	
	public void run() {
		nlpGui = new NLPGui();
	}

	public static void main(String[] args) {
		Application app = new Application();
		app.run();

	}

}
