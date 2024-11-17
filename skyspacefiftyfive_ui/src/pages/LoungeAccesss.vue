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
            <loungeAccess-table
            v-if="loungeAccesss && loungeAccesss.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:loungeAccesss="loungeAccesss"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-lounge-accesss="getAllLoungeAccesss"
             >

            </loungeAccess-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import LoungeAccessTable from "@/components/LoungeAccessTable";
import LoungeAccessService from "../services/LoungeAccessService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    LoungeAccessTable,
  },
  data() {
    return {
      loungeAccesss: [],
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
    async getAllLoungeAccesss(sortBy='loungeAccessId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await LoungeAccessService.getAllLoungeAccesss(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.loungeAccesss.length) {
					this.loungeAccesss = response.data.loungeAccesss;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching loungeAccesss:", error);
        }
        
      } catch (error) {
        console.error("Error fetching loungeAccess details:", error);
      }
    },
  },
  mounted() {
    this.getAllLoungeAccesss();
  },
  created() {
    this.$root.$on('searchQueryForLoungeAccesssChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllLoungeAccesss();
    })
  }
};
</script>
<style></style>
