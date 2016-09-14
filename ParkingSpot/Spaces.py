from datetime import datetime
import os

class ParkingSpot(object):
    def __init__(self, spot_num, spot_type):
        self._spot = spot_num
        self._ticket = None
        self._charge = 0
        self._type = spot_type

    def get_parking_charges(self):
        pass

    def remove_ticket(self):
        self._ticket = None

    def park(self):
        self._ticket = Ticket(vehicle, self)
        return self._ticket

class RegularParkingSpot(ParkingSpot):
    def __init__(self, spot_num):
        super(RegularParkingSpot, self).__init__(spot_num, 'regular')
        self._charge = 10

class VipParkingSpot(ParkingSpot):
    def __init__(self, spot_num):
        super(VipParkingSpot, self).__init__(spot_num, 'vip')
        self._charge = 20

class MotoParkingSpot(ParkingSpot):
    def __init__(self, spot_num):
        super(MotoParkingSpot, self).__init__(spot_num, 'moto')
        self._charge = 5

class ParkingSpotList(object):
    def __init__(self):
        self._max = 0
        self._allocated = 0
        self._next_spot = 0
        self._list = []
        pass

    def is_available(self):
        return self._allocated < self._max

    def self._get_next_spot(self):
        if self._is_available() == False:
            self._next_spot = None

        for i in range(len(self._list)):
            if ps.get_ticket() == None:
               self._next_spot = i 

    def park(self, vehicle):
        pspot = self._list[self._next_spot]
        ticket = pspot.park(vehicle) 
        self._allocated += 1
        self._get_next_spot()
        return ticket

    def unpark(self, spot):
        for ps in self._list:
            if ps.get_spot() == spot.get_spot():
                self._allocated -= 1
                ps.remove_ticket()

class RegularParkingSpotList(ParkingSpotList):
    def __init__(self, plist):
        super(RegularParkingSpotList, self).__init__()
        for i in range(len(plist):
            r = RegularParkingSpot(plist[i])
            self._list.append(r)

        self._max = len(plist)

class VipParkingSpotList(ParkingSpotList):
    def __init__(self, plist):
        super(VipParkingSpotList, self).__init__()
        for i in range(len(plist):
            r = VipParkingSpot(plist[i])
            self._list.append(r)

        self._max = len(plist)

class MotoParkingSpotList(ParkingSpotList):
    def __init__(self, plist):
        super(MotoParkingSpotList, self).__init__()
        for i in range(len(plist):
            r = MotoParkingSpot(plist[i])
            self._list.append(r)

        self._max = len(plist)

class ParkingSpace(object):
    def __init__(self, reg_list, vip_list, moto_list):

        self._reglist = RegularParkingSpotList(reg_list)
        self._viplist = VipParkingSpotList(vip_list)
        self._motolist = MotoParkingSpotList(moto_list) 

    def park(self, vehicle):
        vtype = vehicle.get_type()
        ticket = None
        if vtype == 'regular':
            if self._reglist.available():
                ticket = self._reglist.park(vehicle)
        elif vtype == 'motorcycle':
            if self._motolist.is_available():
                ticket = self._motolist.park(vehicle)
            elif self._reglist.available():
                ticket = self._reglist.park(vehicle)
        elif vtype == 'vip':
            if self._viplist.is_available():
                ticket = self._viplist.park(vehicle)
        return ticket 

    def unpark(self, ticket):
        ticket.charge()
        parkingspot = ticket.get_parking_spot()
        if parkingspot.get_type() == 'reg':
            self._reglist.unpark(parkingspot):
        elif parkingspot.get_type() == 'vip':
            self._viplist.unpark(parkspot):
        elif parkingspot.get_type() == 'moto':
            self._motolist.unpark()

class Ticket(object):
    def __init__(self, Vehicle, ParkingSpot):
        self._vehicle = Vehicle
        self._in_time = datetime.now()
        self._spot = ParkingSpot

    def charge(self):
        self._spot.charge()

class Vehicle(object):
    def __init__(self, type_str):
        self._type = type_str

    def get_type(self):
        return self._type

def main(sys.argv):

    rlist = [ 1, 3, 5, 7 ]
    vlist = [ 2, 8 ]
    mlist = [ 4, 6 ]
    ps = ParkingSpace(rlist, vlist, mlist)
    v = Vehicle('car')

    ticket = ps.park(v)
    ticket = ps.charge(ticket)
    
