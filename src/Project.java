import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class Project {

	public ArrayList<Integer> smoother(String filename, int r){
		try {
			
			File a = new File(filename + ".csv");
			Scanner sc = new Scanner(a);
			ArrayList<Integer> yVal = new ArrayList<Integer>();
			try {
				
				while(sc.hasNextLine()) {
					
					String comma = sc.nextLine();
					
					for(int i = 0;i <= comma.length();) {
						
						int y = Integer.valueOf(comma.substring(comma.indexOf(",")+1));
						yVal.add(y);
						
						break;
					}
				}

			}
			finally {
				sc.close();
			}
			ArrayList<Integer> smoothedList = new ArrayList<Integer>();	
			DescriptiveStatistics smoother = new DescriptiveStatistics();
			smoother.setWindowSize(r);
			for(int i=0;i<yVal.size();i++) {
				smoother.addValue(yVal.get(i));
				smoothedList.add((int)smoother.getMean());
			}
			return smoothedList;
		}
		
		catch(Exception e) {
			System.out.println("Error:" + e.toString());
		}
		
		return null;
	} 
	
	public void print(String filename, String graphTitle, ArrayList<Integer> yVal) throws Exception {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		for(int i=1;i<1000;i++) {
			dataset.addValue(yVal.get(i), graphTitle, String.valueOf(i));
		}
		
		JFreeChart createGraph = ChartFactory.createLineChart(graphTitle, "X", "Y", dataset,PlotOrientation.VERTICAL,true,true,false);
		
		int widthOfImage = 1920;
		int heightOfImage = 1080;
		File xSquaredChart = new File(filename + ".jpeg");
		System.out.println("Graphed");
		ChartUtils.saveChartAsJPEG(xSquaredChart, createGraph, widthOfImage, heightOfImage);
	}
	
}