const member = {
    name: 'lee',
    address: {
        city: 'suwon',
        road_name_address: '460'
    },
    profiles: ['instagram', 'facebook'] ,
    printProfiles: () => {
        member.profiles.map(
            (profile) => {
                console.log(profile)
            }
        )
    }
}

export default function TestJavaScript (){
    return(
        <div>
            <div>{member.name} Test JavaScript</div>
            <div>{member.address.city}, {member.address.road_name_address}</div>
            <div>{member.profiles[0]}</div>
            <div>{member.printProfiles()}</div>
        </div>
    )
}