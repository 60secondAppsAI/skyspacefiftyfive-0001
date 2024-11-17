import http from "../http-common"; 

class SeatMapService {
  getAllSeatMaps(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/seatMap/seatMaps`, searchDTO);
  }

  get(seatMapId) {
    return this.getRequest(`/seatMap/${seatMapId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/seatMap?field=${matchData}`, null);
  }

  addSeatMap(data) {
    return http.post("/seatMap/addSeatMap", data);
  }

  update(data) {
  	return http.post("/seatMap/updateSeatMap", data);
  }
  
  uploadImage(data,seatMapId) {
  	return http.postForm("/seatMap/uploadImage/"+seatMapId, data);
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

export default new SeatMapService();
