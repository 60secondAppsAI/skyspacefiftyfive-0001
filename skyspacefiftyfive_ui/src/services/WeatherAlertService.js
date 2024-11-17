import http from "../http-common"; 

class WeatherAlertService {
  getAllWeatherAlerts(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/weatherAlert/weatherAlerts`, searchDTO);
  }

  get(weatherAlertId) {
    return this.getRequest(`/weatherAlert/${weatherAlertId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/weatherAlert?field=${matchData}`, null);
  }

  addWeatherAlert(data) {
    return http.post("/weatherAlert/addWeatherAlert", data);
  }

  update(data) {
  	return http.post("/weatherAlert/updateWeatherAlert", data);
  }
  
  uploadImage(data,weatherAlertId) {
  	return http.postForm("/weatherAlert/uploadImage/"+weatherAlertId, data);
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

export default new WeatherAlertService();
