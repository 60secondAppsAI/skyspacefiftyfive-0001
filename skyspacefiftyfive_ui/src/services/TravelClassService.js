import http from "../http-common"; 

class TravelClassService {
  getAllTravelClasss(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/travelClass/travelClasss`, searchDTO);
  }

  get(travelClassId) {
    return this.getRequest(`/travelClass/${travelClassId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/travelClass?field=${matchData}`, null);
  }

  addTravelClass(data) {
    return http.post("/travelClass/addTravelClass", data);
  }

  update(data) {
  	return http.post("/travelClass/updateTravelClass", data);
  }
  
  uploadImage(data,travelClassId) {
  	return http.postForm("/travelClass/uploadImage/"+travelClassId, data);
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

export default new TravelClassService();
