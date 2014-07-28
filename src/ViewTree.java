import java.io.File;

public class ViewTree
{
	private GraphViz gv;
	public ViewTree(){
		gv = new GraphViz();
	}
	/**
	 * Construct a DOT graph in memory, convert it
	 * to image and store the image in the file system.
	 */
	public void start()
	{
		gv.addln(gv.start_graph());
//		gv.addln("A -> B;");
	}
	
	public void makeRoot(String rootName){
		gv.addln(rootName);
	}
	
	public void drawEdge(String nodeName1, String nodeName2){
		String s = nodeName1 + " -> " + nodeName2 + ";";
		gv.addln(s);
	}
	
	public void end(){
		gv.addln(gv.end_graph());
		System.out.println(gv.getDotSource());

		String type = "dot";
		File out = new File("c:/Users/Kevin/Desktop/out." + type);    // Windows
		gv.writeGraphToFile( gv.getGraph( gv.getDotSource(), type ), out );
	}
}
