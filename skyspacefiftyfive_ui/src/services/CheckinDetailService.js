import http from "../http-common"; 

class CheckinDetailService {
  getAllCheckinDetails(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/checkinDetail/checkinDetails`, searchDTO);
  }

  get(checkinDetailId) {
    return this.getRequest(`/checkinDetail/${checkinDetailId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/checkinDetail?field=${matchData}`, null);
  }

  addCheckinDetail(data) {
    return http.post("/checkinDetail/addCheckinDetail", data);
  }

  update(data) {
  	return http.post("/checkinDetail/updateCheckinDetail", data);
  }
  
  uploadImage(data,checkinDetailId) {
  	return http.postForm("/checkinDetail/uploadImage/"+checkinDetailId, data);
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

export default new CheckinDetailService();
