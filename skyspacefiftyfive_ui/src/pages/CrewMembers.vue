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
            <crewMember-table
            v-if="crewMembers && crewMembers.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:crewMembers="crewMembers"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-crew-members="getAllCrewMembers"
             >

            </crewMember-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import CrewMemberTable from "@/components/CrewMemberTable";
import CrewMemberService from "../services/CrewMemberService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    CrewMemberTable,
  },
  data() {
    return {
      crewMembers: [],
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
    async getAllCrewMembers(sortBy='crewMemberId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await CrewMemberService.getAllCrewMembers(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.crewMembers.length) {
					this.crewMembers = response.data.crewMembers;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching crewMembers:", error);
        }
        
      } catch (error) {
        console.error("Error fetching crewMember details:", error);
      }
    },
  },
  mounted() {
    this.getAllCrewMembers();
  },
  created() {
    this.$root.$on('searchQueryForCrewMembersChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllCrewMembers();
    })
  }
};
</script>
<style></style>
