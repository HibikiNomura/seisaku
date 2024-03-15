from django.contrib import admin
from django.urls import include, path

urlpatterns = [
    path('admin/', admin.site.urls), # これは元からある
    path("polls/", include("polls.urls")), # これを追加した
]