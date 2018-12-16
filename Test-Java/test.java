import java.io.File;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.io.LineNumberReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

class Test {
	enum eSvc {
		A("A"),
		B("B"),
		C("C");

		private final String name;
		eSvc(final String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	};

	public void test1(String[] args) {
		ArrayList<String> validString = new ArrayList<>(Arrays.asList("xyz", "abc"));
		System.out.println("Hello World! "); 
		if (args.length >= 1 && !validString.contains(args[0])) {
			System.out.println("Hello");
		}
	}

	public void test3(String[] args) {
		List<String> validString = Arrays.asList("xyz", "abc");
		if (args.length >= 1 && validString.contains(args[0])) {
			System.out.println("Found " + args[0]);
		}
	}

	public Stream<String> test4impl1(String name) {
		List<String> subnetPrefixes = Arrays.asList("10.0.0.0/8", "172.16.0.0/12", "192.168.0.0/16");
		return subnetPrefixes.stream().map(m -> m + "," + name);
	}

	public String test4impl2(String name) {
		List<String> subnetPrefixes = Arrays.asList("10.0.0.0/8", "172.16.0.0/12", "192.168.0.0/16");
		return "10.0.0.0/8," + name;
	}

	public void test4(Set<String> names) {
		Stream<String> ms = names.stream().map(n -> test4impl2(n));
		ms.forEach(System.out::println);
	}

	public Stream<String> test6impl1(String arg) {
		List<String> subnetPrefixes = Arrays.asList("10.0.0.0/8", "172.16.0.0/12", "192.168.0.0/16");
		return subnetPrefixes.stream().map(m -> m + "," + arg);
	}

	public Stream<String> test6impl2(String arg, List<String> spec) {
		return spec.stream().map(m -> m + "->" + arg);
	}

	public void test6(Set<String> args) {
		List<String> spec = Arrays.asList("nat1", "nat2", "nat3");
		List<String> ls = args.stream().flatMap(entry -> {
				return Stream.concat(test6impl1(entry), test6impl2(entry, spec));
			}).collect(Collectors.toList());
		ls.forEach(System.out::println);	
	}

	public void test7(String[] args) {
		System.out.println("args : " + args[0] + "," + args[1]);
//		String pmatch = "^([a-z]* )(([a-z]* )([0-9]*)() is running...)";
//		String pmatch = "^([a-z]* )([(a-z]* )([0-9]*)([) is running...])";
		String pmatch = "^([a-z]* ) is stopped";
		Pattern pattern = Pattern.compile(pmatch);
		Matcher matcher = pattern.matcher(args[1]);
		while (matcher.find()) {
			System.out.println("match : " + matcher.group());
		}
		matcher = pattern.matcher(args[1]);
		// if (matcher.find( )) {
		{
			for (int i = 0 ; i <= matcher.groupCount(); i++) {
				System.out.println(matcher.group(i));
			}
		}
	}

	public void test5() {
		List<String> subnetPrefixes = Arrays.asList("10.0.0.0/8", "172.16.0.0/12", "192.168.0.0/16");
		System.out.println("subnetPrefix : " + subnetPrefixes);
		System.out.println("subnetPrefix : " + subnetPrefixes.get(0));
	}

	public void test2(String[] args) {
		String sFileName = "MyTextFile.txt";
		int lineCount;
		LineNumberReader reader = null;

		try {
			reader  = new LineNumberReader(new FileReader(sFileName));
		}
		catch (FileNotFoundException fne) {
			System.out.println (fne.toString());
			System.out.println("Could not find file " + sFileName);
			return;
		}



		String lineRead = "";

		try{
			while ((lineRead = reader.readLine()) != null) {}
		}
		catch(IOException ex){
			System.out.println (ex.toString());
			System.out.println("Could not find file " + sFileName);
		}
	}

	public void test8() {
		Arrays.stream(eSvc.values()).forEach(System.out::println);
	}

	public void test9(String args[]) {
    		File tempReplaceFlowsFile = null;
		try {
			tempReplaceFlowsFile = File.createTempFile(args[0], ".tmp");
			FileOutputStream fileOutputStream = new FileOutputStream(tempReplaceFlowsFile, false);
			PrintWriter replaceFlowsWriter = new PrintWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8), true);

			String tempStr = "table=0, priority=200,in_port=1912,dl_src=00:03:ba:da:0c:b7,arp, actions=load:0x1->NXM_NX_REG0[22..23],load:0x1c5f->NXM_NX_REG0[0..20],goto_table:1";
			Integer limit = Integer.parseInt(args[1]);
			System.out.println("Printing " + args[1] + " entries");
			for (int i=0; i < limit; i++) {
				replaceFlowsWriter.println(tempStr);
			}
			replaceFlowsWriter.flush();
			replaceFlowsWriter.close();
		}
		catch (IOException e) {
			System.out.println("IoException");
		}
	}
	
	public void test10(String args[]) {
	  Integer  regSeconds = Integer.parseInt(args[0]);
	  Long     nanoSec = TimeUnit.NANOSECONDS.convert(regSeconds, TimeUnit.SECONDS);
	  System.out.println("NanoSeconds = " + nanoSec);

	}

	int i = 1000;
	private Map<String, String> conntrackConfigMap = new HashMap<String, String>() {{
	    put("nf_conntrack_buckets", Integer.toString(100));
	}};
	
	public int getI() {
	  return Integer.parseInt(conntrackConfigMap.get("nf_conntrack_buckets"));
	}
	
	private void test9() {
	  Pattern p = Pattern.compile("\\w+ (\\d+) [\\w ]+ (\\d{2}:\\d{2})");
	  Matcher m = p.matcher(oneline);
	  if(m.matches()) {
	      System.out.println("The quantity is " + m.group(1));
	      System.out.println("The time is " + m.group(2));
	  }
	}
	
	public static void main(String args[]) {
		Test t1 = new Test();
		t1.test9();
		// t1.test7(args);
		// t1.test8();
		//t1.test6(Arrays.stream(args).collect(Collectors.toSet()));
		// t1.test9(args);
		// t1.test10(args);
		
		// System.out.println("Print " + t1.getI());
		
		/*
		Double yellowThreshold = 0.8;
		int usedIps = 2;
		int totalIps = 3;
		System.out.println("No Rounding " + (totalIps * yellowThreshold));
		System.out.println("With Rounding " + Math.round(totalIps * yellowThreshold));
		
		Map<Integer, Set<Integer>> e = new ConcurrentHashMap<>();
		e.put(1, new HashSet<>(Arrays.asList(1, 2)));
		e.entrySet().stream().forEach(ent ->
		  System.out.println("Values :" + StringUtils.join(ent.getValue(), ",")));
		  */
		/*
        int i = 0, j = 0, sum = 0, min = Integer.MAX_VALUE;
        int[] nums = {2,3,1,2,4,3};
        int s = 7;
        while (j < nums.length) {
          while (sum < s && j < nums.length) sum += nums[j++];
          System.out.println(" j = " + j);
          if(sum>=s){
              while (sum >= s && i < j) sum -= nums[i++];
              System.out.println(" i = " + i);
              min = Math.min(min, j - i + 1);
          }
          System.out.println(" min = " + min);
        }
        */
	}

}
