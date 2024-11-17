import http from "../http-common"; 

class LoungeAccessService {
  getAllLoungeAccesss(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/loungeAccess/loungeAccesss`, searchDTO);
  }

  get(loungeAccessId) {
    return this.getRequest(`/loungeAccess/${loungeAccessId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/loungeAccess?field=${matchData}`, null);
  }

  addLoungeAccess(data) {
    return http.post("/loungeAccess/addLoungeAccess", data);
  }

  update(data) {
  	return http.post("/loungeAccess/updateLoungeAccess", data);
  }
  
  uploadImage(data,loungeAccessId) {
  	return http.postForm("/loungeAccess/uploadImage/"+loungeAccessId, data);
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

export default new LoungeAccessService();
