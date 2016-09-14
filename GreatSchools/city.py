from download import Download
from file_ops import File
from city_parser import CitySchoolRatingParser

class City(object):
    url_prefix = "http://www.greatschools.org/"
    file_prefix = "./Data/"

    def __init__(self, abbrv, state, city):
        self._abbrv = abbrv
        self._state = state
        self._city = city

    def fetch_data(self):

        url = City.url_prefix + self._state + '/' + self._city
        dest = City.file_prefix + self._abbrv + '/City/' + self._city
        print 'downloading state (%s), city (%s), url (%s), dest (%s)' % (self._state, self._city, url, dest)

        dst_file = File(dest)
        if dst_file.exists() is True:
            print '........Data for %s, %s already present' % (self._state, self._city)
            return

        download = Download(url, dest)
        download.download_cookie()

    def extract_and_publish(self, csvf):
        # parse the data out here
        dest = City.file_prefix + self._abbrv + '/City/' + self._city
        dst_file = File(dest)
        self._rating = CitySchoolRatingParser(dst_file).parse()
        csvf.writerow([self._state, self._city, self._rating]) 
