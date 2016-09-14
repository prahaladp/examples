
import urllib
import urllib2

class Download(object):
    def __init__(self, url, dest):
        self._url = url
        self._dst = dest

    def download(self):
        print 'download %s' % (self._url)
        testfile = urllib.URLopener()
        testfile.retrieve(self._url, self._dst)
        print 'download done'

    def download_cookie(self):
        print 'download %s' % (self._url)
        opener=urllib2.build_opener(urllib2.HTTPCookieProcessor)
        req=urllib2.Request(self._url)
        f = opener.open(req)
        data=f.read()
        f.close()
        opener.close()

        fw = open(self._dst, 'w')
        fw.write(data)
        fw.close()
