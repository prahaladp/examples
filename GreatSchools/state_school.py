import os
from file_ops import Dir
from file_ops import File
from city import City
from city_parser import CityParser
from download import Download
import csv

class State(object):
    state_map = {"AK":"Alaska","AL":"Alabama","AR":"Arkansas","AZ":"Arizona",
                "CA":"California","CO":"Colorado","CT":"Connecticut","DC":"Washington_DC",
                "DE":"Delaware","FL":"Florida","GA":"Georgia","HI":"Hawaii","IA":"Iowa",
                "ID":"Idaho","IL":"Illinois","IN":"Indiana","KS":"Kansas","KY":"Kentucky",
                "LA":"Louisiana","MA":"Massachusetts","MD":"Maryland","ME":"Maine","MI":"Michigan",
                "MN":"Minnesota","MO":"Missouri","MS":"Mississippi","MT":"Montana",
                "NC":"North_Carolina","ND":"North_Dakota","NE":"Nebraska","NH":"New_Hampshire",
                "NJ":"New_Jersey","NM":"New_Mexico","NV":"Nevada","NY":"New_York",
                "OH":"Ohio","OK":"Oklahoma","OR":"Oregon","PA":"Pennsylvania",
                "RI":"Rhode_Island","SC":"South_Carolina","SD":"South_Dakota",
                "TN":"Tennessee","TX":"Texas","UT":"Utah","VA":"Virginia","VT":"Vermont",
                "WA":"Washington","WI":"Wisconsin","WV":"West-Virginia","WY":"Wyoming"};

    url_prefix = "http://www.greatschools.org/schools/cities/california/"
    file_prefix = "./Data/"

    def __init__(self, abbrv):
        self._abbrv = abbrv
        self._state = State.state_map[self._abbrv]
        self._alpha = map(chr, range(65, 91))

    def fetch_cities_in_state(self):
        for alpha in self._alpha:
            url = State.url_prefix + self._abbrv + '/' + alpha
            dest = State.file_prefix + self._abbrv + '/' + alpha
            print 'downloading state (%s), url (%s), state (%s)' % (self._state, url, dest)
            dir = Dir(dest)
            if dir.exists() is False:
                dir.create_if_needed()

            # check if data is present
            data_file = File(dest + '/file')
            if data_file.exists() is True:
                print 'data present for state %s, %s' % (self._state, alpha)
                continue

            download = Download(url, dest + '/file')
            download.download()

    def fetch_cities_data(self):
        dest = State.file_prefix + self._abbrv + '/City'
        dir = Dir(dest)
        if dir.exists() is False:
            dir.create_if_needed()

        for alpha in self._alpha:
            data = File(State.file_prefix + self._abbrv + '/' + alpha + '/file')
            if data.exists() is True:
                # parse and get a list of cities
                print 'parsing city data for (%s, %s)' % (self._state, alpha)
                cities = CityParser(self._abbrv, data)
                for c in cities.parse():
                    this_city = City(self._abbrv, State.state_map[self._abbrv], c) 
                    try:
                        this_city.fetch_data()
                    except Exception:
                        print 'error data for (%s, %s)' % (self._state, alpha)
            else:
                print '...... no data for %s, %s' % (self._abbrv, alpha)

    def extract_city_data(self):
        dest = State.file_prefix + 'Aggregate'
        dir = Dir(dest)
        if dir.exists() is False:
            dir.create_if_needed()

        f = open(dest + '/aggr.csv', 'ab')
        writer=csv.writer(f, delimiter=',')
        for alpha in self._alpha:
            data_path = State.file_prefix + self._abbrv + '/City'
            for root, dirs, files in os.walk(data_path):
                for file in files:
                    this_city = City(self._abbrv, self._state, file)
                    this_city.extract_and_publish(writer)
        f.close()

    def run(self):
        self.fetch_cities_in_state()
        self.fetch_cities_data() 
        self.extract_city_data()
