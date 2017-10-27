class Solution(object):
	def palindrome(self, wa, wb):
		new_word = wa + wb
		nl = len(new_word)
		for i in range(nl):
			j = nl - i - 1
			if i >= j:
				return True
			if new_word[i] != new_word[j]:
				return False

		return True
			

	def palindromPairs(self, words):
		"""
		:type words: List[str]
		:rtype: List[List[int]]
		"""
		ret_list = []
		for i in range(len(words)):
			for j in range(i+1,len(words)):
				if self.palindrome(words[i], words[j]) == True:
					ret_list.append([i, j])
				if self.palindrome(words[j], words[i]) == True:
					ret_list.append([j, i])

		return ret_list

def main():
	words1 = ["bat", "tab", "cat"]
	words2 = ["abcd", "dcba", "lls", "s", "sssll"]
	words3 = ["abcd", "", "lls"]
	words4 = ["hfjdhccfbfhhhghde","jg","dhjdjida","f","dediagbhecegdghaaaid","icdabcebbhehhaahh","eibedacciaa","idfcdhgjgcjcgjig","fhfidbggdghdibifeh","bageigab","eeahjicgdfgcbd","gfeg","g","hbjgiabh","cbhidai","bachcbdcjegcbdijgdfd","icjccejafcaeci","cg","geddabbahgcghgihcica","bggadhcahhae","diejjefbjeb","eieahc","igdbccch","jfdhe","j","hchdaggbjhiccgach","dhiad","ehjdjciehifgbg","hafggdfaeca","b","iddhacfijbiajffehi","icihfiea","cjdechbidfhged","bbhdaecgabfega","bdifcbbhdaaabjj","dajfjhicfbddiciaeibi","hfgchhbhbhdhajhbi","gdifa","bjdcefdjifjb","abeajjadjdfii","eeifadhabcfedbg","gaeadgicfg","hbjhcdaabaifahehfj","iiaebcihfjeaaiijc","hhgidag","hecj","hcgibfbihgacicaj","aaabicdjb","jjjchiacjicfiegggc","bdaagfbeacjjh","eebgbajgie","aaedcicjfbggdjefaaff","ggijjhecha","dcd","gceddjgjhfcfj","giefff","gibeceicg","edfdcbjgchicghcf","djjcjdajjhijjjdhh","iggefcgbfg","jfhjjdcfbefiggcdgi","gadc","iebagfeejchcd","bhadjecgbfdfbabibadb","fdeejfbhijdacfa","gigdedjjbdedgifgghj","ihhhhgib","bbeiiadjbgjfajdbijah","ac","jiacaaebejabfdf","fcjiihhhbjbabbdg","fidgcgidaehbe","ahehh","ggigjddhajhdbafbahj","fhicijfejaabdejea","efjbgafebige","dhdgfdcdfeifdjeebejd","dfjcjjidcghii","ddffjgeddg","egjheejjfjghgha","babgccegagcfidaaidd","jacdab","gfdbb","fdhibbafeffgb","i","bfbbbhggcciie","aajdicfbgbife","deaeaafb","effajbch","ehiffebffadag","ie","ihdjjdieefgdbe","igcidcdbae","fee","igfajj","agebeadadbacbhghf","abgeihfiihefdjjec","cebfgbijbdie","fcih","jfagagaffbjhdhd","aeaghaiidde","aehegcc","jjebb","gjchcdbchcbceddgc","bah","jcfjfdjcjhijfdifad","afdjiefjfcchcejhf","ecjhijdheadfgjbadj","c","chfabceefhif","fjfgcfhffbicjedcd","hjidfic","bhidheggaccjeaf","baiciahegiabj","haaffbhhacfha","icdajb","ghadbbdbigfdjcd","gjdigffdhdjahfeja","jgdbeia","fcjdibbgaaceijcadje","agaij","dghahcfhabeccdjdii","cfbebcggadbbaeidac","dfbdejhdehahjjc","aieabiafibidi","ehe","a","jehidebjj","acecjibeceec","fdbhbhcf","dfcdcicdeeijcghi","ehghggeejccjhj","agfgheea","aiifigdbggef","ffibh","gbfc","digaeag","ijaeee","icjacfd","ccffcghdehgifcfijhd","fhgijaia","iegdhieghgjhajiibe","ahfjcdibefdbgff","ddgbgbdej","fcifidi","bdjddhcaieffega","diegae","ahgfbeegjhighfdh","fbcjcdfifhh","hcajij","bcfbcbiafcjc","egjhefbdibdcibjgah","hdibeidhgfei","ifjgcbhdcbceedi","igjjcgd","ijbgibfbfjhhhcgf","hfdidbiee","egaccifbgeeccbhce","aggih","aaabjehebgh","deifdbcgi","jcbcebdgehbgacfd","dgadijgchfhfabaj","acaiicgdeijajjbbjbih","bfefheifed","jjajccagjdebhbfi","abeadgcgjhjbiahcjhij","cigc","d","ibbdadbfiabacdaii","gj","edafihdjhagjicde","ibgdahbdjebdhbjd","deciacgbagfge","bjdhcefhhj","cdffb","ghhfgadgeh","dfbj","dieefgjedbdbffgdih","ehjda","ffacja","gbcbfgdb","faiahggicabgfah","ahg","egibjhb","fiiafeahaacbafie","ghdgdccjagiajg","edihdfgbadf","bjh","cfgebdga","fbfaefbee","gehchffaibjdgej","iedf","cggccdjf","eejfabbaifjagigjhe","biiedhcgcacef","dfjj","eeegbjgciabaiagafhf","fhbac","cebaeaed","ii","gcg","hicfdbcjheabhe","ejcceabdbaecajacia","bebiaaab","aibfjchihfca","fhecfiadbdhhjgjd","aieidehjjbcfhc","fhhifibhfgbafffgghfj","dfjfiejfhcifaia","ddgeagibgjebehbe","dhe","fegdccjea","afjcdbjgiedaahadada","ebdefficffbabfjib","hedcdajgjcjj","adbfbbaag","fehjhgg","dfdidfh","gbafihehchiiecfjd","ehbbfbgbhfcij","gaiaihbaebdbgagjhc","bjgdcba","dbdiidfacecgfia","habbahi","cicjch","geia","gedgfeaca","ghjcdaicddecjejcjbbf","ejecah","cehiidhefebbcifhidh","jfajahghhihgejif","df","ehc","idecbgbbfcfagi","agghc","iebchg","aaidgiebhgaegagejc","ighbbgeebgedgg","jfhdecjegbcaffedeeh","ecdgfhdhcj","geieedfhddbjacejifc","afiachijidbgb","jgdhebibagiab","eibgdajdfafjhjbefjch","gehhchbghcaefgc","hhicchcaai","bjfcehajfabg","jgebijcebadfhedbjed","hhjdgibhajgdj","fjjbijcfaddc","hab","hiibbbcbafdgjdjjjfad","cfafbgeeb","ffai","agiafgdhgbciaahcc","bgidifibhjbchjec","jjgegfchhjdhj","afgc","bjjdhdbai","bgg","gbhfbjf","aeig","hjfedhhbaejc","gee","ajigidibe","iegchabgafdbgcfiebi","ab","jd","jhifbhagdfe","hbihfaeiibbiaj","ecje","fjbebd","dhbahifecjagg","hj","aabjhcidbfdehigfdij","hhghj","icdacceh","ddiejedbiiejdagcaac","dbadbcjgjjjbchh","jb","fhdcijccffcaefeg","hhaiihefecdjaicbj","fjicca","aagfaj","deaaaf","biejeg","befjgigbbce","bbccii","igjbfihdhhcbjjdgddhb","iaebdfghfdeh","dihibgaijhbbdjjdfge","egahhiiighaijgbidaba","fef","cfeifadjhdbfh","bfdbjjjejcebjdbh","dicggihe","cicdjgeb","fjgejhdchgjdgijbcai","dcbeifdba","hbggjacfjeffjhdhhj","djjbicbijjea","eahbejbjhaebc","bhhgedehh","dg","jaagfcccjhjfbfdjd","ffccceaheddjdfg","bbacha","hbcgfjfabifbhfbjed","eejfficdd","idid","dhf","ghffigiejjgdbjh","efcebfhefiic","fhhgbhiedghibecgj","ca","caaedegefdf","ihcbjhhejfag","iagifii","bagbejjdg","gffgihchfcecgebi","dajfia","fbaj","bgefadaidfhcgcg","jafdcgjaifibjc","jbeaaigddj","egjdhjaheifhecafaj","ecfcfig","dijdhf","ijddacafafhfihi","jhbfdhhbfd","ehjaadchajceagg","eagi","ddhhicbg","addhbd","eehbibfigegffebhjbge","beaegjeaibd","aceaccbbgfecadihjee","gccajbfjdh","jeghcgjejhi","gfdbijjbcefh","iagaaijdiead","gdcbbfcb","dgihjaigadfbggjigeb","dbcibjcfheffb","gfgc","iee","ggecaadafidfafhfddig","ahjeafafiadb","jjijebjbcigiagacj","idijaaefdfdheagabjjj","cceegeajeagd","dfagibjfddjga","hbifcjceeecgfdgchfc","jahfgg","iejadfg","eidegibhffdgh","fcbaefhe","ahffhgcagfjjib","bcgfjigcbhehhdej","e","cbajcaijaabb","jgfaiib","jhcdejaeafceefbgej","icbggbfdacjjchiegb","bfehcb","degajfegedj","hjdcehdg","gjf","idafjaffihhf","bffafeffedehagf","icj","ighjdibbdjidifgiibge","fhhgghgcjcacje","dcjgbgfbbjgjdfaij","jajbbgcf","efjhiejeedefcd","biigiafgfccfgjbhc","baieeiabjigiedih","ejhd","egadigfeaghcggdighf","jhhei","cbcbfgcffjae","igjhbhgeeahjhgdfbe","dcaigidfegdijgcbjc","febjicbgai","bgbgehigcfdei","ghgh","cahg","fjbgbeife","dgiaefhgbcgffb","geigcbijaj","ajggdbfa","iebjfcjbfgiigac","jbhicefbccieggccag","aegababcgdecfjcgdghj","eedafidgcghgggeec","gjefjgihdcedfbjdce","addeci","ahagje"]

	sol = Solution()

#	print "words = %s, solution = %s" % (words1, sol.palindromPairs(words1))
#	print "words = %s, solution = %s" % (words2, sol.palindromPairs(words2))
#	print "words = %s, solution = %s" % (words3, sol.palindromPairs(words3))
	print "words = %s, solution = %s" % (words4, sol.palindromPairs(words4))


if __name__ == "__main__":
        main()


