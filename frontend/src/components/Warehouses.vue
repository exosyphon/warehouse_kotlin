<template>
  <div>
    <div>
      <router-link to="/">Warehouses AGAIN</router-link>
    </div>
    <div>
      <router-link class="other-warehouses" to="/other-warehouses">Other Warehouses</router-link>
    </div>
    <div>{{this.query}}</div>
    <router-view></router-view>
    <ul class="warehouses">
      <li class="warehouse" v-for="warehouse in warehouses" :key="warehouse.id">
        {{warehouse.name}}
      </li>
    </ul>

    <md-dialog class="modal" :md-active.sync="showDialog">
          <md-dialog-title>Preferences</md-dialog-title>

          <md-dialog-actions>
            <md-button class="md-primary" @click="showDialog = false">Close</md-button>
            <md-button class="md-primary" @click="showDialog = false">Save</md-button>
          </md-dialog-actions>
        </md-dialog>

    <md-button class="md-primary md-raised" @click="showDialog = true">Show Dialog</md-button>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';

export default {
  name: "Warehouses",
  computed: Object.assign(
    {},
    {
      query() {
        return window.location.pathname
      }
    },
    mapGetters({
      warehouses: 'getWarehouses'
    })
  ),
  created() {
    this.$store.dispatch('FETCH_WAREHOUSES')
  },
  data: () => ({
        showDialog: false
      })
};
</script>

<style scoped>

</style>
