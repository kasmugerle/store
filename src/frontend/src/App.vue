<template>
  <v-app>
    <v-app-bar app>
      <v-app-bar-nav-icon @click="toggle(drawer)"></v-app-bar-nav-icon>
      <v-toolbar-title>Store</v-toolbar-title>
      <v-spacer></v-spacer>
      <v-btn @click="login()">Login</v-btn>
      <v-btn @click="register()">Register</v-btn>
      <v-btn @click="logout()">Logout</v-btn>
      <v-menu
        left
        bottom
      >
        <template v-slot:activator="{ on }">
          <v-btn icon v-on="on">
            <v-icon>mdi-dots-vertical</v-icon>
          </v-btn>
        </template>
        <v-list>
          <v-list-item v-for="link in routes" :key="link.name" router :to="link.path">
          <v-item-content><v-list-item-title>{{ link.name }}</v-list-item-title></v-item-content>
          </v-list-item>
        </v-list>
      </v-menu>
    </v-app-bar>

    <v-navigation-drawer app v-model="drawer" class="primary">
      <v-list>
          <v-list-item v-for="link in routes" :key="link.name" router :to="link.path">
          <v-item-content><v-list-item-title>{{ link.name }}</v-list-item-title></v-item-content>
          </v-list-item>
        </v-list>
    </v-navigation-drawer>
    <v-content>
      <v-container fluid>
        <router-view></router-view>
      </v-container>
    </v-content>
  </v-app>
</template>

<script>
export default {
  name: 'App',
  data: () => ({
    drawer: false,
  }),
  computed: {
    isLoggedIn() {
      return this.$store.getters.isLoggedIn;
    },
    role () {
      return this.$store.getters.role;
    },
    routes() {
      return this.$router.options.routes;
    }
  },
  methods: {
    logout: function() {
      this.$store.dispatch('logout').then(() => {
         this.$router.push('/login');
      })
    },
    login() {
       this.$router.push('/login');
    },
    register() {
       this.$router.push('/register')
    },
    toggle(show) {
      this.drawer = !show;
    }
  }
}
</script>