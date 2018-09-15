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
  }
};
</script>

<style scoped>

</style>
