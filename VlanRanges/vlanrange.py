import re

# [ ['1', '20'], ['40', '40'] ]
class VlanRange(object):
    def __init__(self, vlanstr):
        self._vlans = []

        tok = re.split(',', vlanstr)
        for t in tok:
            rtok = re.split('-', t)
            if len(rtok) == 1:
                subvlanrange = [ int(rtok[0]), int(rtok[0]) ]
            if len(rtok) == 2:
                subvlanrange = [ int(rtok[0]), int(rtok[1]) ]
            self._vlans.append(subvlanrange)
        print self._vlans

    def _save(self):
        print self._vlans

    def remove(self, vlan):
        print 'removing vlan ', vlan
        bremove = False
        for i in range(len(self._vlans)):
            ent = self._vlans[i]
            if vlan < ent[0]:
                print 'incorrect vlan entry ', vlan, ' < ', ent[0]
                return
            if vlan <= ent[1]:
                # split the entry into two
                pre = []
                post = []
                if ent[0] != ent[1]:
                    pre = [ [ent[0], vlan - 1] ]
                    post = [ [vlan + 1, ent[1]] ]
                self._vlans = self._vlans[-1:i - 1] + pre + post + self._vlans[i + 1 : ]
                bremoved = True
                break

        if bremove == False:
            print 'incorrect vlan entry ', vlan
            return

        self._save()

    def add(self, vlan):
        print 'adding vlan ', vlan
        binserted = False
        indx = 0
        for ent in self._vlans:
            if vlan < ent[0]:
                if vlan == ent[0] + 1:
                    ent[0] = vlan
                    binserted = True
                    break
                elif indx == 0:
                    newentry = [ vlan, vlan ]
                    self._vlans = newentry.append(self._vlans)
                    binserted = True
                    break
                else:
                    self._vlans = self._vlans[0:indx - 1] + [vlan, vlan] + self._vlans[indx:]
                    binserted = True
                    break
            elif vlan < ent[1]:
                break
            elif vlan == ent[1] + 1:
                ent[1] = vlan
                binserted = True
                break
            indx = indx + 1

        if binserted == False:
            newentry = [ vlan, vlan ]
            self._vlans.append(newentry)

        self._save()
            

vl = VlanRange('1-20,40')
vl.remove(14)
vl.add(45)
vl.add(14)
vl.add(41)
vl.remove(45)
vl.remove(1)
vl.add(39)
