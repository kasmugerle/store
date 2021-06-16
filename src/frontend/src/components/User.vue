<template>
    <v-container fluid>
    <v-row>
      <v-col cols="12" sm="6">
        <v-textarea
          label="Name"
          v-model="name"
          auto-grow
          outlined
          rows="1"
          row-height="15"
        ></v-textarea>
      </v-col>
      <v-col cols="12" sm="6">
        <v-textarea
          label="Username"
          v-model="username"
          auto-grow
          outlined
          rows="1"
          row-height="15"
        ></v-textarea>
      </v-col>
      <v-col cols="12" sm="6">
        <v-textarea
          label="Email"
          v-model="email"
          auto-grow
          outlined
          rows="1"
          row-height="15"
        ></v-textarea>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
    data: () => ({
        name: '',
        username: '',
        email: ''
    }),
    methods: {
        getUser() {
            this.$http.get("/api/users").then(response => {
                this.name = response.data.name;
                this.username = response.data.username;
                this.email = response.data.email;
            })
        },
        toCarts() {
            this.$router.push("/carts");
        }
    },
    created() {
        this.getUser();
    }
}
</script>