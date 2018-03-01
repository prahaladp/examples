import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.util.Pair;

public class Solution {
  public List<Integer> findSubstring(String s, String[] words) {
    List<Integer> consInd = new LinkedList<>();
    List<Integer> match = findSubstringImpl(s, words, consInd);
    
    if (match == null) {
      
      match = new LinkedList<Integer>();
      System.out.println("match = " + match);
      return match;
    }
    match = match.stream().distinct().collect(Collectors.toList());
    System.out.println("match = " + match);
    return match;
  }
  
  public List<Integer> findSubstringImpl(String s, String[] words,
      List<Integer> consInd) {
    List<Integer> finalInd = null;
    
    //System.out.println("impl " + s + " consumed " + consInd);
    if (s == null || words == null || consInd == null) {
      return finalInd;
    }
        
    for (int ind = 0; ind < words.length; ind++) {
      if (consInd.contains(ind) == true) {
        continue;
      }
      for (int sInd = 0; sInd < s.length();){
        {
          int matchIndex = s.indexOf(words[ind], sInd);
          //System.out.println("match for " + words[ind] + " is " + matchIndex);
          
          if (matchIndex != -1) {
            if (consInd.isEmpty() == false && matchIndex != 0) {
              // ignore if this is not the first entry
              // System.out.println("Ignoring nonzero match");
              sInd = s.length();
              continue;
            }
            consInd.add(ind);
            
            List<Integer> match = null;
            if ((matchIndex + words[ind].length()) < s.length() &&
                consInd.size() != words.length) {
              match = findSubstringImpl(
                    s.substring(matchIndex + words[ind].length()),
                    words, consInd);
            } else {
              if (consInd.size() == words.length) {
                // all the words have been consumed
                match = new LinkedList<>();
              } else {
                match = null;
              }
            }
            
            consInd.remove(new Integer(ind));         

            if (match != null) {
              if (consInd.isEmpty() == false) {
                return match;
              }
              if (finalInd == null) {
                finalInd = new LinkedList<>();
              }
              finalInd.add(new Integer(matchIndex));
              sInd = matchIndex + 1;
            } else {
              sInd = matchIndex + 1;
            }
          } else {
            sInd = s.length() + 1;
          }
        }
      }
    }
    return finalInd;
  }

  public static <T> Predicate<T> distinctByKey(Function<? super T,Object> keyExtractor) {
    Map<Object,Boolean> seen = new ConcurrentHashMap<>();
    return t -> seen.putIfAbsent(
        keyExtractor.apply(t),
        Boolean.TRUE) == null;
  }

  public static void main(String[] argc) {
    Solution s = new Solution();
    
    Map<String, Integer> sampleStream = new HashMap<>();
    List<String> li = Arrays.asList("a", "b");
    li.stream().map(l -> {
      sampleStream.put(l, 0);
      return l;
    }).collect(Collectors.toList());
    
    final List<String> arpts = Arrays.asList("ABQ","ALB","CHI","CUN","PHX","PUJ","BWI");

    List<String> modifiedArpts = arpts.stream().filter(distinctByKey(f -> f.substring(0,1))).collect(Collectors.toList());
    
    
    s.findSubstring("barfoothefoobarman", new String[]{"foo", "bar"});
    s.findSubstring(null, null);
    s.findSubstring("barfoothefooba", new String[]{"foo", "bar"});
    s.findSubstring("barfoothefoobarmanfoofoobarfoobarbarfoo", new String[]{"foo", "bar"});
    s.findSubstring("wordgoodgoodgoodbestword", new String[]{"word","good","best","good"});
    /*
    s.findSubstring("ejwwmybnorgshugzmoxopwuvshlcwasclobxmckcvtxfndeztdqiakfusswqsovdfwatanwxgtctyjvsmlcoxijrahivwfybbbudosawnfpmomgczirzscqvlaqhfqkithlhbodptvdhjljltckogcjsdbbktotnxgwyuapnxuwgfirbmdrvgapldsvwgqjfxggtixjhshnzphcemtzsvodygbxpriwqockyavfscvtsewyqpxlnnqnvrkmjtjbjllilinflkbfoxdhocsbpirmcbznuioevcojkdqvoraeqdlhffkwqbjsdkfxstdpxryixrdligpzldgtiqryuasxmxwgtcwsvwasngdwovxzafuixmjrobqbbnhwpdokcpfpxinlfmkfrfqrtzkhabidqszhxorzfypcjcnopzwigmbznmjnpttflsmjifknezrneedvgzfmnhoavxqksjreddpmibbodtbhzfehgluuukupjmbbvshzxyniaowdjamlfssndojyyephstlonsplrettspwepipwcjmfyvfybxiuqtkdlzqedjxxbvdsfurhedneauccrkyjfiptjfxmpxlssrkyldfriuvjranikluqtjjcoiqffdxaukagphzycvjtvwdhhxzagkevvuccxccuoccdkbboymjtimdrmerspxpktsmrwrlkvpnhqrvpdekmtpdfuxzjwpvqjjhfaupylefbvbsbhdncsshmrhxoyuejenqgjheulkxjnqkwvzznriclrbzryfaeuqkfxrbldyusoeoldpbwadhrgijeplijcvqbormrqglgmzsprtmryvkeevlthvflsvognbxfjilwkdndyzwwxgdbeqwlldyezmkopktzugxgkklimhhjqkmuaifnodtpredhqygmedtqpezboimeuyyujfjxkdmbjpizpqltvgknnlodtbhnbhjkmuhwxvzgmkhbcvvadhnssbvneecglnqxhavhvxpkjxlluilzpysjcnwguyofnhfvhaceztoiscumkhociglkvispihvyoatxcxbeqsmluixgsliatukrecgoldmzfhwkgaqzsckonjuhxdhqztjfxstjvikdrhpyjfxbjjryslfpqoiphrwfjqqhaamrjbrsiovrxmqsyxhqmritjeauwqbwtpqcqhvyyssvfknfhxvtodpzipueixdbntdfcaeatyyainfpkclbgaaqrwwzwbcjwiqzkwzfuxfclmsxpdyvfbnwxjytnaejivivriamhgqsskqhnqeurttrfrmstrbeokzhuzvbfmwywohmgogyhzpmsdemugqkspsmoppwbnwabdmiruibwznqcuczculujfiavzwynsyqxmarjkshjhxobandwyzggjibjgzyaaqxorqxbkenscbveqbaociwmqxxyzvyblypeongzrttvwqzmrccwkzidyfbxcaypyquodcpwxkstbthuvjqgialhfmgjohzoxvdaxuywfqrgmyahhtpqtazbphmfoluliznftodyguesshcacrsvutylalqrykehjuofisdookjhrljvedsywrlyccpaowjaqyfaqioesxnlkwgpbznzszyudpwrlgrdgwdyhucztsneqttsuirmjriohhgunzatyfrfzvgvptbgpwajgtysligupoqeoqxoyqtzozufvvlktnvahvsseymtpeyfvxttqosgpplkmxwgmsgtpantazppgnubmpwcdqkvhwfuvcahwibniohiqyywnuzzmxeppokxksrfwrpuzqhjgqryorwboxdauhrkxehiwaputeouwxdfoudcoagcxjcuqvenznxxnprgvhasffxtzaxpcfrcovwgrcwqptoekhmgpoywtxruxokcubekzcrqengviwbtgnzvdzrwwkqvacxwgdhffyvjldgvchoiwnfzoyvkiogisdfyjmfomcazigukqlumyzmnzjzhzfpslwsukykwckvktswjdqxdrlsqvsxwxpqkljeyjpulbswwmuhplfueqnvnhukgjarxlxvwmriqjgmxawmndhsvwnjdjvjtxcsjapfogpesxtpypenunfpjuyoevzztctecilqqbxkaqcyhiobvtqgqruumvvhxolbyzsqcrdchhdqprtkkjsccowrjtyjjmkhleanvfpemuublnnyzfabtxsestncfalqenfcswgerbfcqsapzdtscnzugmwlmidtxkvqhbuaecevwhmwkfqmvpgbefpqpsjmdecmixmmbsjxzwvjdmxydechlraajjmoqpcyoqmrjwoiumuzatydzcnktnkeyztoqvogodxxznhvzduzxudwwqhpftwdspuimioanlzobhjakgajafgzxpqckmhdbbnqmcszpuoqbztnftzgahhxwxbgkilnmzfydyxusnnvngksbjabqjaohdvrniezhmxmkxhemwbbclwdxwgngicplzgajmaryzfkyoqlkrmmfirchzrphveuwmvgaxzbwenvteifxuuefnimnadwxhruvoavlzyhfmeasmgrjawongccgfbgoualiaivbhcgvjjnxpggrewglalthmzvgziobrjeanlvyukwlscexbkibvdjhdgnepdiimmkcxhattwglbkicvsfswocbvphmtpwhcgjbnmxgidtlqcnnwtfujhvgzdussqbwynylzvtjapvqtidpdjkpshvrmqlhindhabubyokzdfrwqvnvgzkyhistydagsgnujiviyijdnabfxqbdqnexvwsvzvcsbrmkbkuzsdehghndyqjodnnblfwmaygdstotfkvxozgwhtbhlkvrzismnozqpfthajafuxekzlgigjpsukjvsdihrjzgovnreqwapdkoqswyclqyvbvpedzyoyedvuuamscbxnqnfmmjyehvidnoimmxmtcinwkbqmcobubjjpshucechrqrffqsyscnqoohcsxenypyqhfklloudgmklcejvgynwouzhtfwuuukdbwpmkjrqxeeaipxrokncholathupdetgaktmvmftqjvzyssocftjwemroghrncynmtchhhcaqxbqpthuaafwgrouaxonzocljeuslzsdwvuoodipdpnlhdihaywzmymxdjrqikughquwtenyucjdgrmipiidiwclhuepgyynoslhzahtdqwliktzsddaahohbszhqxxgripqlwlomjbwtuynydoakejmwkvojuwbfltqjfgxqhwkduzbxpdhtpvrzrfjndmsqfizmqxdxtpbpoemekvxzrrakwjxcxqsdasptruqmjtbaapgmkfnbwnlvzlxwdpzfjryanrmzmpzoefapmnsjdgecrdywsabctaegttffigupnwgakylngrrxurtotxqmzxvsqazajvrwsxyeyjteakeudzjxwbjvagnsjntskmocmpgkybqbnwvrwgoskzqkgffpsyhfmxhymqinrbohxlytsmoeleqrjvievpjipsgdkrqeuglrsjnmvdsihicsgkybcjltcswolpsfxdypmlbjotuxewskisnmczfgreuevnjssjifvlqlhkllifxrxkdbjlhcpegmtrelbosyajljvwwedtxbdccpnmreqaqjrxwulpunagwxesbilalrdniqbzxrbpcvmzpyqklsskpwctgqtrjwhrpisocwderqfiqxsdpkphjsapkvhvsqojyixaechvuoemmyqdlfkuzmlliugckuljfkljoshjhlvvlnywvjswvekfyqhjnsusefdtakejxbejrchoncklguqgnyrcslwztbstmycjziuskegagtlonducdogwbevugppsptdqbajmepmmizaycwcgmjeopbivsyphtvxvvgjbyxpgwpganjiaumojpyhhywosrmnouwpstgbrvhtlqcnmqbygbfnabesvshjmdbhyhirfrkqkmfwdgujhzyjdcbyuijjnkqluaczrnrbbwaeeupnwqzbsazplkyaxqorqsshhlljjlpphhedxdepgfgrqerpuhgmaawhnhqwsgnznrfmxjbdrkwjopylxezxgvetcvrwdewsxdeumhzfrvoilmvksuhyqltuimrnsphqslmgvmmojawwptghonigbdclqtbikiacwpjrbxhmzejozpypfixglatdvuogdoizdtsgsztsfcihtgwyqugeuahpuvvzmgarbsyuutmbxuisdfrvbxzxzhmuektssuktoknkfbmcwwubbnwenybmfqglaceuyqnoadzfenjcjfdlvcpiatuhjdujhaffqsvqvuxchgerokejovrqonxxstibunikiedfyahijobxyhimebctobsjudkqstbcxgixgrhpfiofpwruzvpqyjzvollheoldutddnksutjakhtghpxxnjykxjwgqmsvhnykclexepxqxqzghwfxfdhfmflesfabvanxlrurjtigkjotftqnwyskffpxlragrnfffawqtgyfpmzxfpkdpenxlewyxxgrkmwrmshhzfnorolyfxbvdrspxqnxnuoygkruczddgssygfymdcjgvdxutlrhffhnpyjuxmxefrelxezcgikdliyhvpocvvpkvagvmezrxffujeysplvavtjqjxsgujqsjznxforctwzecxyrkwufpdxadrgzczrnyelfschnagucguuqqqwitviynrypsrdswqxqsegulcwrwsjnihxedfcqychqumiscfkwmqqxunqrfbgqjdwmkyelbldxympctbzfupeocwhkypchuyvhybsbmvymjppfrqmlfrbkpjwpyyytytawuuyjrwxboogfessmltwdcssdqtwomymjskujjtmxiueopwacrwfuqazitvyhvlspvoaeipdsjhgyfjbxhityisidnhlksfznubucqxwaheamndjxmcxwufajmnveuwuoyosqnoqwvtjkwuhkzghvmjhawcfszbhzrbpgsidnbmxxihihnrfbamcyojqpkzodbejtmmipahojoysepzhpljpaugrghgjimtdahnpivdtlcnptnxjyiaafislqavamqgmxtdfoiaakorebqpbbpegawrqymqkewycsdjglkiwaacdqterkixkgraedtqirqmjtvsfhadhafktyrmkzmvidxmisfskvevpcnujqxrqedleuyowkjgphsxzzqlvujkwwgiodbfjesnbsbzcnftuzrvzjjudsgcqmmfpnmyrenuxotbbyvxyovzxgtcyzgqnsvcfhczoptnfnojnlinbfmylhdlijcvcxzjhdixuckaralemvsnbgooorayceuedtomzyjtctvtwgyiesxhynvogxnjdjphcftbefxgasawzagfugmuthjahylkhatlgpnkuksuesrduxkodwjzgubpsmzzmvkskzeglxaqrrvmrgcwcnvkhwzbibaxwnriowoavosminabvfxastkcrkdclgzjvqrjofjjvbyfragofeoazzeqljuypthkmywaffmcjkickqqsuhsviyovhitxeajqahshpejaqtcdkuvgdpclnsguabtgbfwdmrmbvydorfrbcokfdmtsgboidkpgpnmdeyhawkqqshtwxdbarwuxykgduxjlkxppwyruihkcqgynjcpbylayvgdqfpbqmshksyfbhrfxxemhgbkgmkhjtkzyzdqmxxwqvdtevyducpdksntgyaqtkrrkwiyuhukfadjvdnrievszilfinxbyrvknfihmetreydbcstkwoexwsfhfekfvfplmxszcosgovisnbemrjlndqwkvhqsofdbdychmupcsxvhazvrihhnxfyumonbvqeyoghccxfuwacxzxqkezxefxarnnujgyjugrzjoefmghjfhcrnbrtgouaehwnnxwkdplodpuqxdbemfwahptpfppjzowoltyqijfoabgzejerpatwponuefgdtcrgxswiddygeeflpjeelzccnsztxfyqhqyhkuppapvgvdtkmxraytcolbhkiiasaazkvqzvfxbaaxkoudovxrjkusxdazxaawmvoostlvvnsfbpjqkijvudpriqrfsrdfortimgdhtypunakzituezjyhbrpuksbamuiycngvlvpyvczfxvlwhjgicvempfobbwadkiavdswyuxdttoqaaykctprkwfmyeodowglzyjzuhencufcwdobydslazxadnftllhmjslfbrtdlahkgwlebdpdeofidldoymakfnpgekmsltcrrnxvspywfggjrmxryybdltmsfykstmlnzjitaipfoyohkmzimcozxardydxtpjgquoluzbznzqvlewtqyhryjldjoadgjlyfckzbnbootlzxhupieggntjxilcqxnocpyesnhjbauaxcvmkzusmodlyonoldequfunsbwudquaurogsiyhydswsimflrvfwruouskxjfzfynmrymyyqsvkajpnanvyepnzixyteyafnmwnbwmtojdpsucthxtopgpxgnsmnsrdhpskledapiricvdmtwaifrhnebzuttzckroywranbrvgmashxurelyrrbslxnmzyeowchwpjplrdnjlkfcoqdhheavbnhdlltjpahflwscafnnsspikuqszqpcdyfrkaabdigogatgiitadlinfyhgowjuvqlhrniuvrketfmboibttkgakohbmsvhigqztbvrsgxlnjndrqwmcdnntwofojpyrhamivfcdcotodwhvtuyyjlthbaxmrvfzxrhvzkydartfqbalxyjilepmemawjfxhzecyqcdswxxmaaxxyifmouauibstgpcfwgfmjlfhketkeshfcorqirmssfnbuqiqwqfhbmol",
        new String[]{"toiscumkhociglkvispihvyoatxcx","ndojyyephstlonsplrettspwepipw","yzfkyoqlkrmmfirchzrphveuwmvga","mxxihihnrfbamcyojqpkzodbejtmm","fenjcjfdlvcpiatuhjdujhaffqsvq","ehghndyqjodnnblfwmaygdstotfkv","heoldutddnksutjakhtghpxxnjykx","cvrwdewsxdeumhzfrvoilmvksuhyq","ftqjvzyssocftjwemroghrncynmtc","idiwclhuepgyynoslhzahtdqwlikt","eurttrfrmstrbeokzhuzvbfmwywoh","jxlluilzpysjcnwguyofnhfvhacez","uskegagtlonducdogwbevugppsptd","xmcxwufajmnveuwuoyosqnoqwvtjk",
            "wolpsfxdypmlbjotuxewskisnmczf","fjryanrmzmpzoefapmnsjdgecrdyw","jgmxawmndhsvwnjdjvjtxcsjapfog","wuhkzghvmjhawcfszbhzrbpgsidnb",
            "yelbldxympctbzfupeocwhkypchuy","vzduzxudwwqhpftwdspuimioanlzo","bdpdeofidldoymakfnpgekmsltcrr","fmyeodowglzyjzuhencufcwdobyds",
            "dhtypunakzituezjyhbrpuksbamui","bdmiruibwznqcuczculujfiavzwyn","eudzjxwbjvagnsjntskmocmpgkybq","tuynydoakejmwkvojuwbfltqjfgxq",
            "psrdswqxqsegulcwrwsjnihxedfcq","cokfdmtsgboidkpgpnmdeyhawkqqs","fujhvgzdussqbwynylzvtjapvqtid","rqeuglrsjnmvdsihicsgkybcjltcs",
            "vhybsbmvymjppfrqmlfrbkpjwpyyy","aukagphzycvjtvwdhhxzagkevvucc","hwkduzbxpdhtpvrzrfjndmsqfizmq",
            "ywnuzzmxeppokxksrfwrpuzqhjgqr","qbajmepmmizaycwcgmjeopbivsyph","uamscbxnqnfmmjyehvidnoimmxmtc","nxvspywfggjrmxryybdltmsfykstm",
            "amrjbrsiovrxmqsyxhqmritjeauwq","yorwboxdauhrkxehiwaputeouwxdf","qkewycsdjglkiwaacdqterkixkgra","ycngvlvpyvczfxvlwhjgicvempfob",
            "jgphsxzzqlvujkwwgiodbfjesnbsb","mkxhemwbbclwdxwgngicplzgajmar","mryvkeevlthvflsvognbxfjilwkdn","mezrxffujeysplvavtjqjxsgujqsj",
            "rtotxqmzxvsqazajvrwsxyeyjteak","sabctaegttffigupnwgakylngrrxu","xccuoccdkbboymjtimdrmerspxpkt","xusnnvngksbjabqjaohdvrniezhmx",
            "oyuejenqgjheulkxjnqkwvzznricl","mxszcosgovisnbemrjlndqwkvhqso","wsgnznrfmxjbdrkwjopylxezxgvet","dxmisfskvevpcnujqxrqedleuyowk",
            "dhrgijeplijcvqbormrqglgmzsprt","vuxchgerokejovrqonxxstibuniki","lumyzmnzjzhzfpslwsukykwckvkts","inwkbqmcobubjjpshucechrqrffqs",
            "ywtxruxokcubekzcrqengviwbtgnz","ccpnmreqaqjrxwulpunagwxesbila","pesxtpypenunfpjuyoevzztctecil","sygfymdcjgvdxutlrhffhnpyjuxmx",
            "uisdfrvbxzxzhmuektssuktoknkfb","cejvgynwouzhtfwuuukdbwpmkjrqx","oudcoagcxjcuqvenznxxnprgvhasf","sxnlkwgpbznzszyudpwrlgrdgwdyh",
            "qqbxkaqcyhiobvtqgqruumvvhxolb","mkhleanvfpemuublnnyzfabtxsest","bibaxwnriowoavosminabvfxastkc","bcxgixgrhpfiofpwruzvpqyjzvoll",
            "lzccnsztxfyqhqyhkuppapvgvdtkm","pdjkpshvrmqlhindhabubyokzdfrw","qbbnhwpdokcpfpxinlfmkfrfqrtzk","rnyelfschnagucguuqqqwitviynry",
            "qtrjwhrpisocwderqfiqxsdpkphjs","vxttqosgpplkmxwgmsgtpantazppg","tyisidnhlksfznubucqxwaheamndj","kgaqzsckonjuhxdhqztjfxstjvikd",
            "jeuslzsdwvuoodipdpnlhdihaywzm","vdzrwwkqvacxwgdhffyvjldgvchoi","cftbefxgasawzagfugmuthjahylkh","xraytcolbhkiiasaazkvqzvfxbaax",
            "oyqtzozufvvlktnvahvsseymtpeyf","rnnujgyjugrzjoefmghjfhcrnbrtg","rfzvgvptbgpwajgtysligupoqeoqx","igbdclqtbikiacwpjrbxhmzejozpy",
            "dyzwwxgdbeqwlldyezmkopktzugxg","hmetreydbcstkwoexwsfhfekfvfpl","zcnftuzrvzjjudsgcqmmfpnmyrenu","zzmvkskzeglxaqrrvmrgcwcnvkhwz",
            "vjswvekfyqhjnsusefdtakejxbejr","rwwzwbcjwiqzkwzfuxfclmsxpdyvf","fdbdychmupcsxvhazvrihhnxfyumo","vdtevyducpdksntgyaqtkrrkwiyuh",
            "nbvqeyoghccxfuwacxzxqkezxefxa","vpgbefpqpsjmdecmixmmbsjxzwvjd","jwgqmsvhnykclexepxqxqzghwfxfd","olyfxbvdrspxqnxnuoygkruczddgs",
            "qgmxtdfoiaakorebqpbbpegawrqym","liaivbhcgvjjnxpggrewglalthmzv","choncklguqgnyrcslwztbstmycjzi","fpkdpenxlewyxxgrkmwrmshhzfnor",
            "hhhcaqxbqpthuaafwgrouaxonzocl","ipahojoysepzhpljpaugrghgjimtd","wosrmnouwpstgbrvhtlqcnmqbygbf","nwyskffpxlragrnfffawqtgyfpmzx",
            
            "bcvvadhnssbvneecglnqxhavhvxpk","hoavxqksjreddpmibbodtbhzfehgl","lazxadnftllhmjslfbrtdlahkgwle","uuukupjmbbvshzxyniaowdjamlfss",
            "tpqtazbphmfoluliznftodyguessh","ychqumiscfkwmqqxunqrfbgqjdwmk","rkdclgzjvqrjofjjvbyfragofeoaz","pphhedxdepgfgrqerpuhgmaawhnhq",
            "cacrsvutylalqrykehjuofisdookj","kyldfriuvjranikluqtjjcoiqffdx","bnwvrwgoskzqkgffpsyhfmxhymqin","uzmlliugckuljfkljoshjhlvvlnyw",
            
            "abfxqbdqnexvwsvzvcsbrmkbkuzsd","xotbbyvxyovzxgtcyzgqnsvcfhczo","bwtpqcqhvyyssvfknfhxvtodpzipu","nsfbpjqkijvudpriqrfsrdfortimg",
            "tgwyqugeuahpuvvzmgarbsyuutmbx","upnwqzbsazplkyaxqorqsshhlljjl","edfyahijobxyhimebctobsjudkqst","ialhfmgjohzoxvdaxuywfqrgmyahh",""
                + "jlhcpegmtrelbosyajljvwwedtxbd","tpfppjzowoltyqijfoabgzejerpat","mgogyhzpmsdemugqkspsmoppwbnwa","nubmpwcdqkvhwfuvcahwibniohiqy",
                "ukfadjvdnrievszilfinxbyrvknfi","dgnepdiimmkcxhattwglbkicvsfsw","syqxmarjkshjhxobandwyzggjibjg","bnwxjytnaejivivriamhgqsskqhnq",
                "hzyjdcbyuijjnkqluaczrnrbbwaee","yscnqoohcsxenypyqhfklloudgmkl","habidqszhxorzfypcjcnopzwigmbz","wjdqxdrlsqvsxwxpqkljeyjpulbsw",
                
                "tytawuuyjrwxboogfessmltwdcssd","pfixglatdvuogdoizdtsgsztsfcih","apkvhvsqojyixaechvuoemmyqdlfk","ouaehwnnxwkdplodpuqxdbemfwahp",
                "ixuckaralemvsnbgooorayceuedto","ymxdjrqikughquwtenyucjdgrmipi","smrwrlkvpnhqrvpdekmtpdfuxzjwp","bhjakgajafgzxpqckmhdbbnqmcszp",
                "beqsmluixgsliatukrecgoldmzfhw","greuevnjssjifvlqlhkllifxrxkdb","yzsqcrdchhdqprtkkjsccowrjtyjj","sviyovhitxeajqahshpejaqtcdkuv",
                "qtwomymjskujjtmxiueopwacrwfuq","mzyjtctvtwgyiesxhynvogxnjdjph","dyfbxcaypyquodcpwxkstbthuvjqg","hfmflesfabvanxlrurjtigkjotftq",
                "mxydechlraajjmoqpcyoqmrjwoium","nabesvshjmdbhyhirfrkqkmfwdguj","bhrfxxemhgbkgmkhjtkzyzdqmxxwq","gziobrjeanlvyukwlscexbkibvdjh",
                "mcwwubbnwenybmfqglaceuyqnoadz","xyzvyblypeongzrttvwqzmrccwkzi","ncfalqenfcswgerbfcqsapzdtscnz","dtqpezboimeuyyujfjxkdmbjpizpq","wmuhplfueqnvnhukgjarxlxvwmriq"
                ,"qwapdkoqswyclqyvbvpedzyoyedvu","uoqbztnftzgahhxwxbgkilnmzfydy","zsddaahohbszhqxxgripqlwlomjbw","bwadkiavdswyuxdttoqaaykctprkw",
                "eixdbntdfcaeatyyainfpkclbgaaq","nmjnpttflsmjifknezrneedvgzfmn","avlzyhfmeasmgrjawongccgfbgoua","kklimhhjqkmuaifnodtpredhqygme",
                "xzbwenvteifxuuefnimnadwxhruvo","ugmwlmidtxkvqhbuaecevwhmwkfqm","rhpyjfxbjjryslfpqoiphrwfjqqha","eeaipxrokncholathupdetgaktmvm",
                "ltuimrnsphqslmgvmmojawwptghon","azitvyhvlspvoaeipdsjhgyfjbxhi","efrelxezcgikdliyhvpocvvpkvagv","znxforctwzecxyrkwufpdxadrgzcz",
                "kcqgynjcpbylayvgdqfpbqmshksyf","hrljvedsywrlyccpaowjaqyfaqioe","cjmfyvfybxiuqtkdlzqedjxxbvdsf","zeqljuypthkmywaffmcjkickqqsuh",
                "wnfzoyvkiogisdfyjmfomcazigukq","zyaaqxorqxbkenscbveqbaociwmqx","ahnpivdtlcnptnxjyiaafislqavam","edtqirqmjtvsfhadhafktyrmkzmvi","wponuefgdtcrgxswiddygeeflpjee","xozgwhtbhlkvrzismnozqpfthajaf","ptnfnojnlinbfmylhdlijcvcxzjhd","uxekzlgigjpsukjvsdihrjzgovnre","rbohxlytsmoeleqrjvievpjipsgdk","fxtzaxpcfrcovwgrcwqptoekhmgpo","tvxvvgjbyxpgwpganjiaumojpyhhy","vqjjhfaupylefbvbsbhdncsshmrhx","urhedneauccrkyjfiptjfxmpxlssr","ltvgknnlodtbhnbhjkmuhwxvzgmkh","ucztsneqttsuirmjriohhgunzatyf","rbzryfaeuqkfxrbldyusoeoldpbwa","atlgpnkuksuesrduxkodwjzgubpsm","lrdniqbzxrbpcvmzpyqklsskpwctg","qvnvgzkyhistydagsgnujiviyijdn","uzatydzcnktnkeyztoqvogodxxznh","ocbvphmtpwhcgjbnmxgidtlqcnnwt","koudovxrjkusxdazxaawmvoostlvv","ptruqmjtbaapgmkfnbwnlvzlxwdpz","xdxtpbpoemekvxzrrakwjxcxqsdas","gdpclnsguabtgbfwdmrmbvydorfrb","htwxdbarwuxykgduxjlkxppwyruih"
        });
      */
    
      Map<Integer, Map<Integer, Set<String>>> igwSet = new ConcurrentHashMap<>();
      Map<Integer, Set<String>> igwNetSet1 = new ConcurrentHashMap<>();
      Set<String> igwNetIpSet1Address = new HashSet<>();
      igwNetIpSet1Address.add("1.1.1.1");
      igwNetIpSet1Address.add("1.1.1.3");
      igwNetSet1.put(1, igwNetIpSet1Address);
      Set<String> igwNetIpSet11Address = new HashSet<>();
      igwNetIpSet11Address.add("11.1.1.1");
      igwNetIpSet11Address.add("11.1.1.3");
      igwNetSet1.put(1, igwNetIpSet1Address);
      igwNetSet1.put(11, igwNetIpSet11Address);


      igwSet.put(1, igwNetSet1);

      
      Map<Integer, Set<String>> igwNetSet2 = new ConcurrentHashMap<>();
      Set<String> igwNetIpSet2Address = new HashSet<>();
      igwNetIpSet2Address.add("2.2.2.2");
      igwNetIpSet2Address.add("2.2.2.2");
      igwNetSet2.put(2, igwNetIpSet2Address);
      igwSet.put(2, igwNetSet2);
      
      System.out.println("Original Set = " + igwSet);

      System.out.println("Flattening the igwSet : " +
          igwSet.entrySet().stream().flatMap(ent -> ent.getValue().entrySet().stream().map(netEnt -> {
              return netEnt.getValue().stream().collect(Collectors.toList());
            }
          )).collect(Collectors.toList()));
      
      Map<Integer, List<String>> igwMap = new ConcurrentHashMap<>();
      
      igwSet.entrySet().stream().flatMap(ent ->
        ent.getValue().entrySet().stream()
          .map(netEnt -> {
            return new Pair<Integer, List<String>>(ent.getKey(), netEnt.getValue().stream().collect(Collectors.toList()));
          }
        )).forEach(ent -> { 
          System.out.println(ent.getKey() + " " + ent.getValue());
          List<String> currentIps = igwMap.computeIfAbsent(ent.getKey(), k -> new LinkedList<>());
          System.out.println(ent.getKey() + " in map " + currentIps);
          currentIps.addAll(ent.getValue());
          System.out.println("new value " + currentIps);

          igwMap.put(ent.getKey(), currentIps);
        });
      
      System.out.println("Map " + igwMap);
      
      Map<Integer, Set<String>> igwToPriorityMap = new ConcurrentHashMap<>();

      igwSet.entrySet().stream().map(ent -> 
        new Pair<Integer, Optional<Set<String>>>(ent.getKey(),
            ent.getValue().entrySet().stream().map(ne -> ne.getValue()).findFirst())
      ).forEach(e -> {
        if (e.getValue().isPresent()) {
          igwToPriorityMap.put(e.getKey(), e.getValue().get());
        }
      }
      );
      
      System.out.println("IgwNetPriorityMap" + igwToPriorityMap);

      
      String existingActions = "conjunction(100000003, 1/4),conjunction(100000001, 1/4)";
      String actionList = "conjunction(100000002, 1/4)";
      if (actionList.compareTo(existingActions) > 0) {
        actionList = actionList + "," + existingActions;
      } else {
        actionList = existingActions + "," + actionList;
      }
      
      Map<String, TreeSet<String>> dstVnic = new HashMap<>();
      List<String> testInput = Arrays.asList("conjunction(100000003, 1/4)", "conjunction(1000000030, 1/4)", "conjunction(100000001, 1/4)");
      
      testInput.stream().forEach(entry -> {
        TreeSet<String> eS = dstVnic.get("entry");
        if (eS == null) {
          eS = new TreeSet<>();
          dstVnic.put("entry", eS);
        }
        eS.add(entry);
      });

      Stream<String> actionStr = dstVnic.entrySet().stream().flatMap(
          entry -> {
            String match = entry.getKey();
            TreeSet<String> actionSet = entry.getValue();
            String action = String.join(",", actionSet);
            return Stream.of(String.format("%s actions=%s", match, action));
          }
      );
      
      actionStr.forEach(elem -> System.out.println(elem));
    }
}
