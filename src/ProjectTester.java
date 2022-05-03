public class ProjectTester {
	public static void main(String[] args) throws Exception {
		Project p = new Project();
		String saltyFileName = "Salty";
		String smoothedFileName = "Smoothy";
		String smoothedGraphTitle = "Smoothed Graph";
		int smoothRange = 7;
		
		p.print(smoothedFileName,smoothedGraphTitle,p.smoother(saltyFileName,smoothRange));
	}
}