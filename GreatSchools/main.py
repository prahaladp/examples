
from state_school import State

def main():
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
                "WA":"Washington","WI":"Wisconsin","WV":"West_Virginia","WY":"Wyoming"};

    for key in state_map:
        state = State(key)
        state.run()

if __name__ == '__main__':
    main()

