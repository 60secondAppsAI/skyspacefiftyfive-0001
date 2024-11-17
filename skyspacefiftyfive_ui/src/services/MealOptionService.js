import http from "../http-common"; 

class MealOptionService {
  getAllMealOptions(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/mealOption/mealOptions`, searchDTO);
  }

  get(mealOptionId) {
    return this.getRequest(`/mealOption/${mealOptionId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/mealOption?field=${matchData}`, null);
  }

  addMealOption(data) {
    return http.post("/mealOption/addMealOption", data);
  }

  update(data) {
  	return http.post("/mealOption/updateMealOption", data);
  }
  
  uploadImage(data,mealOptionId) {
  	return http.postForm("/mealOption/uploadImage/"+mealOptionId, data);
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

export default new MealOptionService();
