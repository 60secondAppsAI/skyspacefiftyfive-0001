<template>
  <div class="content">
    <div class="row">
      <div class="col-12">
        <card class="card-plain">
          <!-- <template slot="header">
            <h4 class="card-title">Table on Plain Background</h4>
            <p class="category">Here is a subtitle for this table</p>
          </template>-->
          <div class="table-full-width text-left">
            <weatherAlert-table
            v-if="weatherAlerts && weatherAlerts.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:weatherAlerts="weatherAlerts"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-weather-alerts="getAllWeatherAlerts"
             >

            </weatherAlert-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import WeatherAlertTable from "@/components/WeatherAlertTable";
import WeatherAlertService from "../services/WeatherAlertService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    WeatherAlertTable,
  },
  data() {
    return {
      weatherAlerts: [],
	  totalElements: 0,
      page: 0,
      searchQuery: '',     
      table1: {
        title: "Simple Table",
        columns: [...tableColumns],
        data: [...tableData],
      },
    };
  },
  methods: {
    async getAllWeatherAlerts(sortBy='weatherAlertId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await WeatherAlertService.getAllWeatherAlerts(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.weatherAlerts.length) {
					this.weatherAlerts = response.data.weatherAlerts;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching weatherAlerts:", error);
        }
        
      } catch (error) {
        console.error("Error fetching weatherAlert details:", error);
      }
    },
  },
  mounted() {
    this.getAllWeatherAlerts();
  },
  created() {
    this.$root.$on('searchQueryForWeatherAlertsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllWeatherAlerts();
    })
  }
};
</script>
<style></style>
