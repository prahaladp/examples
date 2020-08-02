import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

class Solution {
	class Result {
		public String res;
		public int len;
		public Result(String s, int l) {
			res = s;
			len = l;
		}
	}
	
	public interface TestInterface {
		public class Args {
			int	t;
			public Args(int tval) {
				t = tval;
			}
		}
	}
	
	public class TestClass {
		public class Args {
			int	t;
			public Args(int tval) {
				t = tval;
			}
		}
	}
	
	public String compareWord(String q, String w) {
		int indx = 0;
		
		System.out.println("Comparing " + q + " with " + w);
		HashSet<Character> vowels = new HashSet<>(
				Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
		if (w.length() != q.length()) {
			return "";
		}
		
		for(Character c : q.toCharArray()) {
			Character uc = Character.toLowerCase(c);
			Character uw = Character.toLowerCase(w.charAt(indx));
			if (c == (w.charAt(indx)) || uc == (w.charAt(indx)) || uw == uc) {
				indx++;
				continue;
			}
			if (vowels.contains(uc) == true && vowels.contains(w.charAt(indx)) == true) {
				indx++;
				continue;
			}
			System.out.println("return empty" + indx);
			return "";
		}
		return w;
	}
	
	private String checkWords(String q, String[] wordlist) {
		String res = "";
		for(String w : wordlist) {
			if (q.compareTo(w) == 0) {
				return q;
			} else if ( q.toLowerCase().compareTo(w) == 0) {
				return w;
			} else {
				String r = compareWord(q, w);
				if (r.compareTo("") != 0 && res.compareTo("") == 0) {
					System.out.println("Updating query " + q + " to " + r);
					res = r;
				}
			}
		}
		return res;
	}
	
    public String[] spellchecker(String[] wordlist, String[] queries) {

    		/* for each of the queries, check if each of the letters match */
    		/* for each of the words, if it is a vowel, check if it is a different vowel */
    		/* for each of the words, if the match is different only in case, return that */
    		List<String> results = Arrays.asList(queries).stream()
    			.map(q -> {
    				return checkWords(q, wordlist);
    			})
    			.collect(Collectors.toList());
    		
    		return results.stream().toArray(String[]::new);
    }
    
    public static class TestStaticClass {
    	
    }
    
    public static void main(String[] args) {
    		//String[] wordlist = {"KiTe","kite","hare","Hare"};
    		//String[] queries = {"kite","Kite","KiTe","Hare","HARE","Hear","hear","keti","keet","keto"};
    		//String[] wordlist = {"kkk","hrt","fze","awj","dfn","kec","zss","dkp","pdx","pgm","ozl","dhj","uqm","eks","opv","cxo","okq","wym","fjp","yyo","awz","lsp","quk","hhe","sth","mpo","mbg","smj","cpm","dno","miq","fld","zxa","fdu","ige","lmt","gyh","wcu","wiv","zad","tjk","ync","voc","gqw","fzk","ehs","kgp","hde","kkp","tko","uae","uax","xhm","anh","oph","lws","amw","vyi","lec","exq","dbx","gee","cbp","pfu","uya","loi","zba","qdo","cfv","oxg","him","aoj","uob","kxs","odx","qtu","xbg","bqy","imr","dzo","ona","hat","jxd","bae","ops","len","fof","wlt","fxa","ryu","qay","asd","shj","wbi","moz","gsi","hdc","abt","yfd","ptc","dyj","dhg","qwj","zme","enn","lfc","pdn","vcx","aig","ywr","txj","ngl","mro","rqc","baf","vbr","box","wgv","ifa","ogw","ikg","aai","qeg","bgs","cmo","prf","trt","rqq","sep","uqx","xvn","uzw","fof","mzz","qug","pnq","kwd","igf","yly","ecb","bcz","osc","onq","khy","ubi","iik","cee","ora","iyt","soa","qdo","cmr","hty","jap","ghn","gwh","cqd","tre","hix","ztg","zhf","mbx","esc","hzo","tic","mpi","gvt","gmm","tnp","qgb","riv","yrw","bvu","uia","mnx","lnh","wao","pxz","haw","bix","qmr","kga","umh","lmk","noi","mjx","erj","yda","dny","zsk","qla","ndq","atn","hkb","svh","tvi","pyw","foa","zuo","ort","ous","orx","vfk","vus","fwz","nfg","vsd","opn","nqm","hru","jrt","ymi","xty","dph","etf","kiu","dpa","paa","oug","vca","ejn","hrl","auc","idt","vuz","dxr","dzc","crg","cyw","eiq","owp","qye","aiy","rmb","laf","fmu","csn","ray","ckd","rhg","dvf","guk","suw","nfv","poe","qpj","tlg","rxv","iuu","adj","sjh","ocw","ytn","ptt","kdg","anu","dsl","nhb","ywm","bok","nlb","wcf","tor","hlr","rvw","xui","hxc","knm","oyb","dgz","puu","ovo","obi","neb","zfo","ouz","mcc","fcd","xzd","mtu","dpg","zre","tba","hsz","lqv","tfv","pbp","glf","dhc","dzw","zso","cuf","jek","gqd","wyr","gip","wsp","wus","emv","kbc","ssg","gvu","eme","fwa","zeo","ljy","rkv","iiw","ljl","iwn","oqo","kcd","bhl","dyo","mho","scr","zfg","iqr","zxo","unq","omd","vck","cux","ivh","xrw","ata","jgd","mtu","zhb","ahd","zcl","zvi","fgq","htq","epe","vgi","khc","mdm","nwq","bbx","iqz","eys","irl","ihz","zhd","nsa","ele","pst","xyq","kox","qys","tlv","uwr","boi","fdt","amb","lyq","nej","xxr","ixx","ust","hwe","hla","ykp","qyf","sny","bci","yid","gii","dci","irn","mjp","wvk","zys","cxs","hua","uji","oxn","flj","uac","yoz","qcx","fsk","wvp","vtq","zsw","uvw","zqi","bgu","udg","dnb","ehz","dtu","atp","cop","unj","zse","vzv","mjx","xwr","mlv","mlv","vky","dkl","kat","ufp","hyi","vzd","zok","bel","saz","aba","jgx","uvc","yir","lid","zph","uuh","gti","lcl","oxf","yib","xpa","bwf","udc","bom","nkm","lkz","rqw","ihl","bwy","jmf","pfy","hbu","imn","eyf","nkz","gje","svc","ovt","pdd","ukl","zxb","mdr","kzp","oxi","gtv","raw","shy","cau","vgx","nrg","bfg","qzn","knc","srq","qdx","lij","ixc","ogc","noj","jxo","usr","ytl","muv","uti","pbe","dzb","rvp","fqt","hhx","mhe","cga","gtd","yat","zac","lbt","gke","tuh","obz","vuv","gmq","dki","skv","qbm","nbb","ugv","hxt","uxn","uaq","qqa","koe","fxc","sgj","hvx","nae","wtp","njm","mnb","rge"};
    		String[] wordlist = {"zuo", "zeo" };
    		String[] queries = {"Zeo", "nAe", "kOe"};
    		Solution s = new Solution();
    		
    		// String[] ans =  s.spellchecker(wordlist, queries);
    		//System.out.println("Matching = " + String.join(",", Arrays.asList(ans)));
    		
    		//["kite","KiTe","KiTe","Hare","hare","","","KiTe","","KiTe"]
    		//System.out.println(s.compareWord("Kite", "KiTe"));
    		
    		// TestInterface.Args tArgs = new TestInterface.Args(1);
    		
    		Instant i = Instant.now();
    		String dt = i.toString();
    		System.out.println("instant to date is  " + dt);
    		//LocalDateTime localDateTime = LocalDateTime.parse(dt, DateTimeFormatter.ISO_INSTANT);
    		// LocalDate localDate = LocalDate.parse("2011-12-03T10:15:30Z", DateTimeFormatter.ISO_INSTANT);
    		//LocalDate localDate = LocalDate.from(DateTimeFormatter.ISO_INSTANT.parse("2020-06-04T05:15:32.380"));
    		// ZonedDateTime zonedDate = ZonedDateTime.parse("2011-12-03T10:15:30Z", DateTimeFormatter.ISO_INSTANT);
    		Instant ni = Instant.parse(dt);
    }
}