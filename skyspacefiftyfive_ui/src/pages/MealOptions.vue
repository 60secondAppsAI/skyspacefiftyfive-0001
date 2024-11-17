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
            <mealOption-table
            v-if="mealOptions && mealOptions.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:mealOptions="mealOptions"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-meal-options="getAllMealOptions"
             >

            </mealOption-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import MealOptionTable from "@/components/MealOptionTable";
import MealOptionService from "../services/MealOptionService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    MealOptionTable,
  },
  data() {
    return {
      mealOptions: [],
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
    async getAllMealOptions(sortBy='mealOptionId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await MealOptionService.getAllMealOptions(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.mealOptions.length) {
					this.mealOptions = response.data.mealOptions;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching mealOptions:", error);
        }
        
      } catch (error) {
        console.error("Error fetching mealOption details:", error);
      }
    },
  },
  mounted() {
    this.getAllMealOptions();
  },
  created() {
    this.$root.$on('searchQueryForMealOptionsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllMealOptions();
    })
  }
};
</script>
<style></style>
