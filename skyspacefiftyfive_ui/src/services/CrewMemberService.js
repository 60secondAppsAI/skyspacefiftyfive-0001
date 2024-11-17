import http from "../http-common"; 

class CrewMemberService {
  getAllCrewMembers(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/crewMember/crewMembers`, searchDTO);
  }

  get(crewMemberId) {
    return this.getRequest(`/crewMember/${crewMemberId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/crewMember?field=${matchData}`, null);
  }

  addCrewMember(data) {
    return http.post("/crewMember/addCrewMember", data);
  }

  update(data) {
  	return http.post("/crewMember/updateCrewMember", data);
  }
  
  uploadImage(data,crewMemberId) {
  	return http.postForm("/crewMember/uploadImage/"+crewMemberId, data);
  }




	postRequest(url, data) {
		return http.post(url, data);
      };

	getRequest(url, params) {
        return http.get(url, {
        	params: params
        });
    };

}

export default new CrewMemberService();
